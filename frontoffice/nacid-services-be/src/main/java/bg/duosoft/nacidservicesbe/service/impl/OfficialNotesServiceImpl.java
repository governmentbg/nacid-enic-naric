package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNoteKind;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesDetailsDTO;
import bg.duosoft.nacidservicesbe.cloner.entity.common.ApplicationAttachedDocCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.common.ApplicationDocumentReceiveMethodEntityCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.officialnote.OfficialNoteDetailsEntityCloner;
import bg.duosoft.nacidservicesbe.cloner.entity.officialnote.OfficialNoteEntityCloner;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationDocumentReceiveMethodEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.OfficialNoteDetailsEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.OfficialNoteEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.OfficialNoteFullEntity;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.OfficialNotesEvaluations;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.officialnotes.OfficialNotesApplicationMapper;
import bg.duosoft.nacidservicesbe.mapper.officialnotes.OfficialNotesDetailsMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.lib.OfficialNotesFullRepository;
import bg.duosoft.nacidservicesbe.repository.lib.OfficialNotesRepository;
import bg.duosoft.nacidservicesbe.service.OfficialNotesService;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.utils.ApplicationEntityUtils;
import bg.duosoft.nacidservicesbe.service.utils.AttachmentsEntityUtils;
import bg.duosoft.nacidservicesbe.service.utils.OfficialNotesEntityUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.02.2023
 * Time: 14:16
 */
@Service
@RequiredArgsConstructor
public class OfficialNotesServiceImpl extends BaseCommonApplicationServiceImpl<OfficialNotesApplicationDTO, CommonApplicantDetailsDTO, OfficialNotesDetailsDTO, OfficialNoteFullEntity> implements OfficialNotesService {

    private final CommonApplicantDetailsMapper commonApplicantDetailsMapper;
    private final OfficialNotesDetailsMapper officialNotesDetailsMapper;
    private final OfficialNotesApplicationMapper officialNotesApplicationMapper;

    private final OfficialNotesFullRepository officialNotesFullRepository;
    private final OfficialNotesRepository officialNotesRepository;

    private final OfficialNotesEvaluations officialNotesEvaluations;

    private final OfficialNoteEntityCloner officialNoteEntityCloner;
    private final OfficialNoteDetailsEntityCloner officialNoteDetailsEntityCloner;
    private final ApplicationAttachedDocCloner applicationAttachedDocCloner;
    private final ApplicationDocumentReceiveMethodEntityCloner applicationDocumentReceiveMethodEntityCloner;

    @Override
    @Transactional
    public OfficialNotesDetailsDTO saveRequestSpecificDetails(Integer applicationId, OfficialNotesDetailsDTO notesDetails) {
        List<OfficialNoteDetailsEntity> officialNoteDetails = officialNotesDetailsMapper.toEntityList(notesDetails.getOfficialNotesKinds());

        OfficialNoteFullEntity applicationToSave = officialNotesFullRepository.findById(applicationId).orElseThrow();
        applicationToSave.getOfficialNoteDetails().clear();
        applicationToSave.getOfficialNoteDetails().addAll(officialNoteDetails);
        applicationToSave.setDetailedInformation(notesDetails.getAdditionalInformation());

        OfficialNotesEntityUtils.preSaveOfficialNoteDetails(applicationToSave);
        OfficialNoteFullEntity saved = officialNotesFullRepository.save(applicationToSave);

        OfficialNotesDetailsDTO toReturn = new OfficialNotesDetailsDTO();
        toReturn.setOfficialNotesKinds(officialNotesDetailsMapper.toDtoList(saved.getOfficialNoteDetails()));
        toReturn.setAdditionalInformation(saved.getDetailedInformation());
        toReturn.setServiceType(saveServiceType(applicationId, notesDetails));
        return toReturn;
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(OfficialNotesApplicationDTO application) {
        if (application.getOfficialNotesDetails() != null && application.getOfficialNotesDetails().getOfficialNotesKinds() != null) {
            return createOfficialNotesFeeCalculationParamsMap(application.getOfficialNotesDetails() != null && application.getOfficialNotesDetails().getServiceType() != null ? application.getOfficialNotesDetails().getServiceType().getId() : null, application.getOfficialNotesDetails().getOfficialNotesKinds());
        } else {
            return createOfficialNotesFeeCalculationParamsMap(null, new ArrayList<>());
        }
    }

    @Override
    public Map<String, String> createOfficialNotesFeeCalculationParamsMap(String serviceType, List<OfficialNoteKind> kinds) {
        Map<String, String> params = new HashMap<>();
        Boolean dissertation = false;
        Boolean paper = false;
        Boolean position = false;
        Boolean project = false;
        for (OfficialNoteKind kd : kinds) {
            switch (kd) {
                case DISSERTATION_NOTE:
                    dissertation = true;
                    break;
                case PAPER_NOTE:
                    paper = true;
                    break;
                case POSITION_NOTE:
                    position = true;
                    break;
                case PROJECT_NOTE:
                    project = true;
                    break;
            }
        }

        params.put(PaymentService.APPLICATION_SUBTYPE_PARAM, getInitialApplicationSubtype().getCode());
        params.put(PaymentService.DISSERTATION_NOTE_FLAG_PARAM, dissertation.toString());
        params.put(PaymentService.PAPER_NOTE_FLAG_PARAM, paper.toString());
        params.put(PaymentService.POSITION_NOTE_FLAG_PARAM, position.toString());
        params.put(PaymentService.PROJECT_NOTE_FLAG_PARAM, project.toString());
        params.put(PaymentServiceImpl.SERVICE_TYPE_PARAM, serviceType);
        return params;
    }

    @Override
    public ApplicationType getInitialApplicationType() {
        return ApplicationType.LIBRARY;
    }

    @Override
    public ApplicationSubtype getInitialApplicationSubtype() {
        return ApplicationSubtype.OFFICIAL_NOTE;
    }

    @Override
    @Transactional
    public void createNewRequest(Integer applicationId) {
        OfficialNoteEntity officialNoteEntity = new OfficialNoteEntity();
        officialNoteEntity.setId(applicationId);

        officialNotesRepository.save(officialNoteEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfficialNoteFullEntity> prepareEntitiesToSaveOnFileFinalize(Integer mainApplicationId) {
        OfficialNoteFullEntity originalEntity = officialNotesFullRepository.findById(mainApplicationId).orElseThrow();
        if (originalEntity.getOfficialNoteDetails() != null && originalEntity.getOfficialNoteDetails().size() > 1) {
            List<OfficialNoteFullEntity> finalEntityList = new ArrayList<>();
            List<OfficialNoteDetailsEntity> originalDetailsList = new ArrayList<>(originalEntity.getOfficialNoteDetails());

            originalEntity.getOfficialNoteDetails().clear();
            originalEntity.getOfficialNoteDetails().add(originalDetailsList.get(0));
            finalEntityList.add(originalEntity);

            originalDetailsList.remove(0);

            for (OfficialNoteDetailsEntity orgDetails : originalDetailsList) {
                OfficialNoteFullEntity cloned = officialNoteEntityCloner.clone(originalEntity);
                ApplicationEntityUtils.preSaveClonedApplication(cloned.getApplication());
                cloned.setApplication(getApplicationRepository().save(cloned.getApplication()));
                cloned.setId(cloned.getApplication().getId());

                OfficialNoteDetailsEntity clonedDetails = officialNoteDetailsEntityCloner.clone(orgDetails);
                cloned.setOfficialNoteDetails(Arrays.asList(clonedDetails));
                OfficialNotesEntityUtils.preSaveOfficialNoteDetails(cloned);

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
        return officialNotesFullRepository;
    }

    @Override
    public BaseObjectMapper getFullApplicationMapper() {
        return officialNotesApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<OfficialNotesApplicationDTO> getEvaluationsComponent() {
        return officialNotesEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_LIBRARY;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "officialNotesReceipt.ftl";
    }
}
