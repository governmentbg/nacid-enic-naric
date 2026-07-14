package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.biblioreference.BiblioReferenceApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.biblioreference.BibliographicReferenceDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidservicesbe.cloner.entity.common.ApplicationAttachedDocCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.biblioreference.BibliographicReferenceEntityCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.common.ApplicationDocumentReceiveMethodEntityCloner;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationDocumentReceiveMethodEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.*;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.BibliographicReferenceEvaluations;
import bg.duosoft.nacidservicesbe.mapper.biblioreference.BibliographicReferenceApplicationMapper;
import bg.duosoft.nacidservicesbe.mapper.biblioreference.BibliographicReferenceDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.lib.BibliographicReferenceFullRepository;
import bg.duosoft.nacidservicesbe.repository.lib.BibliographicReferenceRepository;
import bg.duosoft.nacidservicesbe.service.BibliographicReferenceService;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.utils.ApplicationEntityUtils;
import bg.duosoft.nacidservicesbe.service.utils.AttachmentsEntityUtils;
import bg.duosoft.nacidservicesbe.service.utils.BibliographicReferenceEntityUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.03.2023
 * Time: 14:26
 */
@Service
@RequiredArgsConstructor
public class BibliographicReferenceServiceImpl extends BaseCommonApplicationServiceImpl<BiblioReferenceApplicationDTO, CommonApplicantDetailsDTO, BibliographicReferenceDetailsDTO, BibliographicReferenceFullEntity> implements BibliographicReferenceService {

    private final CommonApplicantDetailsMapper commonApplicantDetailsMapper;
    private final BibliographicReferenceApplicationMapper bibliographicReferenceApplicationMapper;
    private final BibliographicReferenceDetailsMapper bibliographicReferenceDetailsMapper;

    private final BibliographicReferenceFullRepository bibliographicReferenceFullRepository;
    private final BibliographicReferenceRepository bibliographicReferenceRepository;

    private final BibliographicReferenceEvaluations bibliographicReferenceEvaluations;

    private final BibliographicReferenceEntityCloner bibliographicReferenceEntityCloner;
    private final ApplicationAttachedDocCloner applicationAttachedDocCloner;
    private final ApplicationDocumentReceiveMethodEntityCloner applicationDocumentReceiveMethodEntityCloner;

    @Override
    @Transactional
    public BibliographicReferenceDetailsDTO saveRequestSpecificDetails(Integer applicationId, BibliographicReferenceDetailsDTO biblioDetails) {
        BibliographicReferenceFullEntity sourceToCopyFrom = bibliographicReferenceDetailsMapper.toEntity(biblioDetails);
        BibliographicReferenceFullEntity toSave = bibliographicReferenceFullRepository.findById(applicationId).orElseThrow();

        BibliographicReferenceDetailsMapper.copyDetailsToApplication(toSave, sourceToCopyFrom);

        BibliographicReferenceEntityUtils.preSaveLanguages(toSave);
        BibliographicReferenceFullEntity saved = bibliographicReferenceFullRepository.save(toSave);

        return bibliographicReferenceDetailsMapper.toDto(saved);
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(BiblioReferenceApplicationDTO application) {
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
        return ApplicationSubtype.BIBLIO_REFERENCE;
    }

    @Override
    @Transactional
    public void createNewRequest(Integer applicationId) {
        BibliographicReferenceEntity biblioEntity = new BibliographicReferenceEntity();
        biblioEntity.setId(applicationId);

        bibliographicReferenceRepository.save(biblioEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BibliographicReferenceFullEntity> prepareEntitiesToSaveOnFileFinalize(Integer mainApplicationId) {
        BibliographicReferenceFullEntity originalEntity  = bibliographicReferenceFullRepository.findById(mainApplicationId).orElseThrow();
        if(originalEntity.getSearchBgFlag() == 1 && originalEntity.getSearchForeignFlag() == 1){
            BibliographicReferenceFullEntity cloned = bibliographicReferenceEntityCloner.clone(originalEntity);
            ApplicationEntityUtils.preSaveClonedApplication(cloned.getApplication());
            cloned.setApplication(getApplicationRepository().save(cloned.getApplication()));
            cloned.setId(cloned.getApplication().getId());
            cloned.getApplication().setAttachedDocs(applicationAttachedDocCloner.cloneList(originalEntity.getApplication().getAttachedDocs(), cloned.getApplication().getId(), cloned.getApplication().getDateCreated().toLocalDate()));
            cloned.setSearchBgFlag(0);
            cloned.setResultKindCodeBg(null);
            BibliographicReferenceEntityUtils.preSaveLanguages(cloned);
            AttachmentsEntityUtils.preSaveAttachedDocs(cloned.getApplication().getAttachedDocs(), cloned.getApplication().getId());

            List<ApplicationDocumentReceiveMethodEntity> clonedDocumentReceiveMethods = applicationDocumentReceiveMethodEntityCloner.cloneList(originalEntity.getApplication().getApplicationDocumentReceiveMethods());
            cloned.getApplication().setApplicationDocumentReceiveMethods(clonedDocumentReceiveMethods);
            ApplicationEntityUtils.preSaveDocumentReceiveMethodDetails(cloned.getApplication());

            originalEntity.setSearchForeignFlag(0);
            originalEntity.setResultKindCodeForeign(null);

            return Arrays.asList(originalEntity, cloned);
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
        return bibliographicReferenceFullRepository;
    }

    @Override
    public BaseObjectMapper getFullApplicationMapper() {
        return bibliographicReferenceApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<BiblioReferenceApplicationDTO> getEvaluationsComponent() {
        return bibliographicReferenceEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_LIBRARY;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "bibliographicReferenceReceipt.ftl";
    }
}
