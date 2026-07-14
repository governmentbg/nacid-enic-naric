package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.document.DocumentDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocBibliographicDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocDeliveryApplicationDTO;
import bg.duosoft.nacidservicesbe.controller.utils.AccessUtils;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.DocDeliveryService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.documentdelivery.DocBibliographicDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.documentdelivery.DocDeliveryApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.documentdelivery.DocDeliveryFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 13:39
 */
@Api(tags = Tags.DOCUMENT_DELIVERY)
@RestController
@RequestMapping("/api/v1/document-delivery")
@RequiredArgsConstructor
@Slf4j
public class DocDeliveryController extends BaseApplicationController<DocDeliveryApplicationDTO, CommonApplicantDetailsDTO, DocBibliographicDetailsDTO>{

    private final DocDeliveryService docDeliveryService;
    private final DocDeliveryApplicantDetailsValidator docDeliveryApplicantDetailsValidator;
    private final DocBibliographicDetailsValidator docBibliographicDetailsValidator;
    private final DocDeliveryFilingValidator docDeliveryFilingValidator;

    @Override
    public DocBibliographicDetailsDTO saveAppSpecificDetails(@RequestParam Integer id, @RequestBody DocBibliographicDetailsDTO specificDetails){
        AccessUtils.checkAccessAllowedForAppModification(id, getCommonApplicationService());
        AccessUtils.checkAppModificationAllowedDependingOnStatus(id, getCommonApplicationService());
        BadRequestValidator.validateRequest(getApplicationSpecificDetailsValidator(), specificDetails);
        DocBibliographicDetailsDTO detailsWithoutRemoved = new DocBibliographicDetailsDTO();
        detailsWithoutRemoved.setEntries(specificDetails.getEntries().stream().filter(e -> !e.isForRemoval()).collect(Collectors.toList()));
        moveNotPersistedFiles(detailsWithoutRemoved.getEntries(), createApplicationFileStoreRelativePath(id));
        preSaveRequestSpecificDetails(detailsWithoutRemoved);
        DocBibliographicDetailsDTO saved = (DocBibliographicDetailsDTO) getApplicationService().saveRequestSpecificDetails(id, detailsWithoutRemoved);
        try {
            removeUnnecessaryFiles(specificDetails.getEntries());
        } catch (Exception e){
            log.error("Residual files might not been removed correctly when saving document delivery details", e);
        }
        return saved;
    }

    @Override
    public DocumentDetailsDTO saveDocumentDetails(Integer id, DocumentDetailsDTO documentDetails) {
        throw new RuntimeException("Saving document details is not available for document delivery services");
    }

    @Override
    public BaseApplicationService getApplicationService() {
        return docDeliveryService;
    }

    @Override
    public Validator<CommonApplicantDetailsDTO> getApplicantDetailsValidator() {
        return docDeliveryApplicantDetailsValidator;
    }

    @Override
    public Validator<DocBibliographicDetailsDTO> getApplicationSpecificDetailsValidator() {
        return docBibliographicDetailsValidator;
    }

    @Override
    public Validator<DocDeliveryApplicationDTO> getFilingValidator() {
        return docDeliveryFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(CommonApplicantDetailsDTO applicantDetails) {
        ApplicantDetailsDTOUtils.preSaveCommonApplicantDetails(applicantDetails, getBoPublicServicesService());
    }
}
