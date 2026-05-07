package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.biblioreference.BiblioReferenceApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.biblioreference.BibliographicReferenceDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidservicesbe.controller.utils.ApplicantDetailsDTOUtils;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.BibliographicReferenceService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.biblioreference.BibliographicReferenceApplicantDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.biblioreference.BibliographicReferenceDetailsValidator;
import bg.duosoft.nacidservicesbe.validation.biblioreference.BibliographicReferenceFilingValidator;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.03.2023
 * Time: 15:46
 */
@Api(tags = Tags.BIBLIOGRAPHIC_REFERENCE)
@RestController
@RequestMapping("/api/v1/bibliographic-reference")
@RequiredArgsConstructor
public class BibliographicReferenceController extends BaseApplicationController<BiblioReferenceApplicationDTO, CommonApplicantDetailsDTO, BibliographicReferenceDetailsDTO>{

    private final BibliographicReferenceService bibliographicReferenceService;
    private final BibliographicReferenceApplicantDetailsValidator bibliographicReferenceApplicantDetailsValidator;
    private final BibliographicReferenceDetailsValidator bibliographicReferenceDetailsValidator;
    private final BibliographicReferenceFilingValidator bibliographicReferenceFilingValidator;

    @Override
    public BaseApplicationService getApplicationService() {
        return bibliographicReferenceService;
    }

    @Override
    public Validator<CommonApplicantDetailsDTO> getApplicantDetailsValidator() {
        return bibliographicReferenceApplicantDetailsValidator;
    }

    @Override
    public Validator<BibliographicReferenceDetailsDTO> getApplicationSpecificDetailsValidator() {
        return bibliographicReferenceDetailsValidator;
    }

    @Override
    public Validator<BiblioReferenceApplicationDTO> getFilingValidator() {
        return bibliographicReferenceFilingValidator;
    }

    @Override
    public void preSaveApplicantDetails(CommonApplicantDetailsDTO applicantDetails) {
        ApplicantDetailsDTOUtils.preSaveCommonApplicantDetails(applicantDetails, getBoPublicServicesService());
    }

    @Override
    public void preSaveRequestSpecificDetails(BibliographicReferenceDetailsDTO specificDetails) {
        if(!Boolean.TRUE.equals(specificDetails.getForeignSearch())){
            specificDetails.setForeignSearchKind(null);
        }
        if(!Boolean.TRUE.equals(specificDetails.getNacidSearch())){
            specificDetails.setNacidSearchKind(null);
        }
    }
}
