package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryDetailsDTO;
import bg.duosoft.nacidservicesbe.cloner.entity.common.ApplicationAttachedDocCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.common.ApplicationDocumentReceiveMethodEntityCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.inquiry.InquiryEntityCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.inquiry.InquiryKindEntityCloner;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationDocumentReceiveMethodEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.*;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.InquiryEvaluations;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.inquiry.InquiryApplicationMapper;
import bg.duosoft.nacidservicesbe.mapper.inquiry.InquiryDetailsMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.lib.InquiryFullRepository;
import bg.duosoft.nacidservicesbe.repository.lib.InquiryRepository;
import bg.duosoft.nacidservicesbe.service.InquiryService;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.utils.ApplicationEntityUtils;
import bg.duosoft.nacidservicesbe.service.utils.AttachmentsEntityUtils;
import bg.duosoft.nacidservicesbe.service.utils.InquiryEntityUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.02.2023
 * Time: 12:12
 */
@Service
@RequiredArgsConstructor
public class InquiryServiceImpl extends BaseCommonApplicationServiceImpl<InquiryApplicationDTO, CommonApplicantDetailsDTO, InquiryDetailsDTO, InquiryFullEntity> implements InquiryService {

    private final CommonApplicantDetailsMapper commonApplicantDetailsMapper;
    private final InquiryDetailsMapper inquiryDetailsMapper;
    private final InquiryApplicationMapper inquiryApplicationMapper;

    private final InquiryFullRepository inquiryFullRepository;
    private final InquiryRepository inquiryRepository;

    private final InquiryEvaluations inquiryEvaluations;

    private final InquiryEntityCloner inquiryEntityCloner;
    private final InquiryKindEntityCloner inquiryKindEntityCloner;
    private final ApplicationAttachedDocCloner applicationAttachedDocCloner;
    private final ApplicationDocumentReceiveMethodEntityCloner applicationDocumentReceiveMethodEntityCloner;

    @Override
    @Transactional
    public InquiryDetailsDTO saveRequestSpecificDetails(Integer applicationId, InquiryDetailsDTO inquiryDetails) {
        InquiryFullEntity inquiryDetailsToSave = inquiryDetailsMapper.toEntity(inquiryDetails);

        InquiryFullEntity applicationToSave = inquiryFullRepository.findById(applicationId).orElseThrow();
        InquiryDetailsMapper.copyDetailsToApplication(applicationToSave, inquiryDetailsToSave);

        InquiryEntityUtils.preSaveInquiryDetails(applicationToSave);
        InquiryFullEntity saved = inquiryFullRepository.save(applicationToSave);

        InquiryDetailsDTO toReturn = inquiryDetailsMapper.toDto(saved);
        return toReturn;
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(InquiryApplicationDTO application) {
        Map<String, String> params = new HashMap<>();
        params.put(PaymentService.APPLICATION_SUBTYPE_PARAM, getInitialApplicationSubtype().getCode());
        return params;
    }

    @Override
    public ApplicationType getInitialApplicationType() {
        return ApplicationType.LIBRARY;
    }

    @Override
    public ApplicationSubtype getInitialApplicationSubtype() {
        return ApplicationSubtype.INQUIRY;
    }

    @Override
    @Transactional
    public void createNewRequest(Integer applicationId) {
        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setId(applicationId);

        inquiryRepository.save(inquiryEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InquiryFullEntity> prepareEntitiesToSaveOnFileFinalize(Integer mainApplicationId) {
        InquiryFullEntity originalEntity = inquiryFullRepository.findById(mainApplicationId).orElseThrow();
        if(originalEntity.getInquiryKinds() != null && originalEntity.getInquiryKinds().size()>0){
            List<InquiryFullEntity> finalEntityList = new ArrayList<>();
            List<InquiryKindEntity> originalKindsList = new ArrayList<>(originalEntity.getInquiryKinds());

            originalEntity.getInquiryKinds().clear();
            originalEntity.getInquiryKinds().add(originalKindsList.get(0));
            finalEntityList.add(originalEntity);

            originalKindsList.remove(0);

            for(InquiryKindEntity orgKind: originalKindsList){
                InquiryFullEntity cloned = inquiryEntityCloner.clone(originalEntity);
                ApplicationEntityUtils.preSaveClonedApplication(cloned.getApplication());
                cloned.setApplication(getApplicationRepository().save(cloned.getApplication()));
                cloned.setId(cloned.getApplication().getId());

                InquiryKindEntity clonedKind = inquiryKindEntityCloner.clone(orgKind);
                cloned.setInquiryKinds(Arrays.asList(clonedKind));
                InquiryEntityUtils.preSaveInquiryDetails(cloned);

                List<ApplicationDocumentReceiveMethodEntity> clonedDocumentReceiveMethods = applicationDocumentReceiveMethodEntityCloner.cloneList(originalEntity.getApplication().getApplicationDocumentReceiveMethods());
                cloned.getApplication().setApplicationDocumentReceiveMethods(clonedDocumentReceiveMethods);
                ApplicationEntityUtils.preSaveDocumentReceiveMethodDetails(cloned.getApplication());

                cloned.getApplication().setAttachedDocs(applicationAttachedDocCloner.cloneList(originalEntity.getApplication().getAttachedDocs(), cloned.getApplication().getId(), cloned.getApplication().getDateCreated().toLocalDate()));
                AttachmentsEntityUtils.preSaveAttachedDocs(cloned.getApplication().getAttachedDocs(), cloned.getApplication().getId());
                finalEntityList.add(cloned);
            }
            return finalEntityList;
        } else {
            return Arrays.asList(originalEntity);
        }
    }

    @Override
    public BaseObjectMapper<ApplicationEntity, CommonApplicantDetailsDTO> getApplicantDetailsMapper() {
        return commonApplicantDetailsMapper;
    }

    @Override
    public FullApplicationRepositoryBase getFullApplicationRepository() {
        return inquiryFullRepository;
    }

    @Override
    public BaseObjectMapper getFullApplicationMapper() {
        return inquiryApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<InquiryApplicationDTO> getEvaluationsComponent() {
        return inquiryEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_LIBRARY;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "inquiryReceipt.ftl";
    }
}
