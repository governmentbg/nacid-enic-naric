package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocBibliographicDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocDeliveryApplicationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.*;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.DocDeliveryEvaluations;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.documentdelivery.DocBibliographicEntryDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.documentdelivery.DocDeliveryApplicationMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.lib.DocumentDeliveryFullRepository;
import bg.duosoft.nacidservicesbe.repository.lib.DocumentDeliveryRepository;
import bg.duosoft.nacidservicesbe.service.DocDeliveryService;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.utils.DocumentDeliveryEntityUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 13:27
 */
@Service
@RequiredArgsConstructor
public class DocDeliveryServiceImpl extends BaseCommonApplicationServiceImpl<DocDeliveryApplicationDTO, CommonApplicantDetailsDTO, DocBibliographicDetailsDTO, DocumentDeliveryFullEntity> implements DocDeliveryService {

    private final CommonApplicantDetailsMapper commonApplicantDetailsMapper;
    private final DocBibliographicEntryDetailsMapper docBibliographicEntryDetailsMapper;
    private final DocDeliveryApplicationMapper docDeliveryApplicationMapper;

    private final DocumentDeliveryFullRepository documentDeliveryFullRepository;
    private final DocumentDeliveryRepository documentDeliveryRepository;

    private final DocDeliveryEvaluations docDeliveryEvaluations;

    @Override
    @Transactional
    public DocBibliographicDetailsDTO saveRequestSpecificDetails(Integer applicationId, DocBibliographicDetailsDTO specificDetails) {
        List<DocumentDeliveryDetailsEntity> detailsToSave = docBibliographicEntryDetailsMapper.toEntityList(specificDetails.getEntries());

        DocumentDeliveryFullEntity applicationToSave = documentDeliveryFullRepository.findById(applicationId).orElseThrow();
        applicationToSave.getDeliveryDetails().clear();
        applicationToSave.getDeliveryDetails().addAll(detailsToSave);

        DocumentDeliveryEntityUtils.preSaveDocumentDeliveryDetails(applicationToSave);

        DocumentDeliveryFullEntity saved = documentDeliveryFullRepository.save(applicationToSave);
        DocBibliographicDetailsDTO toReturn = new DocBibliographicDetailsDTO();
        toReturn.setEntries(docBibliographicEntryDetailsMapper.toDtoList(saved.getDeliveryDetails()));
        return toReturn;
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(DocDeliveryApplicationDTO application) {
        Map<String, String> params = new HashMap<>();
        params.put(PaymentService.APPLICATION_SUBTYPE_PARAM, getInitialApplicationSubtype().getCode());
        return params;
    }

    @Override
    @Transactional
    public void deleteApplication(Integer id) {
        Optional<DocumentDeliveryFullEntity> docOpt = documentDeliveryFullRepository.findById(id);
        if(docOpt.isPresent()){
            DocumentDeliveryFullEntity doc = docOpt.get();
            List<DocumentDeliveryDetailsEntity> docDetails = doc.getDeliveryDetails();

            super.deleteApplication(id);

            if(docDetails != null){
                docDetails.stream().filter(details -> StringUtils.hasText(details.getFileId())).forEach(detail -> {
                    getFileService().removeFile(detail.getRootDirectory(), detail.getRelativePath(), detail.getFileId());
                });
            }
        }
    }

    @Override
    public ApplicationType getInitialApplicationType() {
        return ApplicationType.LIBRARY;
    }

    @Override
    public ApplicationSubtype getInitialApplicationSubtype() {
        return ApplicationSubtype.DOCUMENT_SERVICE;
    }

    @Override
    @Transactional
    public void createNewRequest(Integer applicationId) {
        DocumentDeliveryEntity docDeliveryEntity = new DocumentDeliveryEntity();
        docDeliveryEntity.setId(applicationId);

        documentDeliveryRepository.save(docDeliveryEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentDeliveryFullEntity> prepareEntitiesToSaveOnFileFinalize(Integer mainApplicationId) {
        return Arrays.asList(documentDeliveryFullRepository.findById(mainApplicationId).orElseThrow());
    }

    @Override
    public BaseObjectMapper<ApplicationEntity, CommonApplicantDetailsDTO> getApplicantDetailsMapper() {
        return commonApplicantDetailsMapper;
    }

    @Override
    public FullApplicationRepositoryBase getFullApplicationRepository() {
        return documentDeliveryFullRepository;
    }

    @Override
    public BaseObjectMapper getFullApplicationMapper() {
        return docDeliveryApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<DocDeliveryApplicationDTO> getEvaluationsComponent() {
        return docDeliveryEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_LIBRARY;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "documentDeliveryReceipt.ftl";
    }
}
