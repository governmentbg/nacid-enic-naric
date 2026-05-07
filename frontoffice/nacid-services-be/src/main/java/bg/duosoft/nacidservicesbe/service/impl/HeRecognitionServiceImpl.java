package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.*;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.herecognition.HeRecognitionApplicationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiApplicationFullEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseEntity;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.HeRecognitionEvaluations;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.RudiApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.herecognition.HeEducationDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.herecognition.HeRecognitionApplicationMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.rudi.RudiApplicationFullRepository;
import bg.duosoft.nacidservicesbe.repository.rudi.RudiApplicationRepository;
import bg.duosoft.nacidservicesbe.repository.rudi.RudiTrainingCourseRepository;
import bg.duosoft.nacidservicesbe.service.HeRecognitionService;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.utils.TrainingCourseEntityUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 09.11.2022
 * Time: 15:10
 */
@Service("heRecognitionService")
@RequiredArgsConstructor
public class HeRecognitionServiceImpl extends BaseCommonApplicationServiceImpl<HeRecognitionApplicationDTO, RudiApplicantDetailsDTO, HeEducationDetailsDTO, RudiApplicationFullEntity> implements HeRecognitionService {

    private final RudiApplicantDetailsMapper rudiApplicantDetailsMapper;
    private final HeEducationDetailsMapper heEducationDetailsMapper;

    private final HeRecognitionApplicationMapper heRecognitionApplicationMapper;

    private final RudiApplicationRepository rudiApplicationRepository;
    private final RudiApplicationFullRepository rudiApplicationFullRepository;
    private final RudiTrainingCourseRepository rudiTrainingCourseRepository;

    private final HeRecognitionEvaluations heRecognitionEvaluations;

    @Override
    @Transactional
    public HeEducationDetailsDTO saveRequestSpecificDetails(Integer applicationId, HeEducationDetailsDTO educationDetails) {
        RudiTrainingCourseEntity toSave = heEducationDetailsMapper.toEntity(educationDetails);

        RudiTrainingCourseEntity fetchedFromDB = rudiTrainingCourseRepository.findByRudiApplicationId(applicationId);
        TrainingCourseEntityUtils.keepTrainingCourseDBDetails(fetchedFromDB, toSave, applicationId);
        TrainingCourseEntityUtils.preSaveTrainingCourse(toSave);

        RudiTrainingCourseEntity saved = rudiTrainingCourseRepository.save(toSave);
        HeEducationDetailsDTO toReturn = heEducationDetailsMapper.toDto(saved);
        return toReturn;
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(HeRecognitionApplicationDTO application) {
        Map<String, String> params = new HashMap<>();
        params.put(PaymentService.APPLICATION_SUBTYPE_PARAM, getInitialApplicationSubtype().getCode());
        params.put(PaymentService.LEGAL_TYPE_PARAM, application.getApplicantDetails().getApplicant().getApplicantType().getLegalTypeCode());
        return params;
    }

    @Override
    public ApplicationType getInitialApplicationType() {
        return ApplicationType.ACADEMIC_RECOGNITION;
    }

    @Override
    public ApplicationSubtype getInitialApplicationSubtype() {
        return ApplicationSubtype.HE_RECOGNITION;
    }

    @Override
    @Transactional
    public void createNewRequest(Integer applicationId) {
        RudiApplicationEntity rudiApplicationEntity = new RudiApplicationEntity();
        rudiApplicationEntity.setId(applicationId);

        rudiApplicationRepository.save(rudiApplicationEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RudiApplicationFullEntity> prepareEntitiesToSaveOnFileFinalize(Integer mainApplicationId) {
        return Arrays.asList(rudiApplicationFullRepository.findById(mainApplicationId).orElseThrow());
    }

    @Override
    public BaseObjectMapper<ApplicationEntity, RudiApplicantDetailsDTO> getApplicantDetailsMapper() {
        return rudiApplicantDetailsMapper;
    }

    @Override
    public FullApplicationRepositoryBase getFullApplicationRepository() {
        return rudiApplicationFullRepository;
    }

    @Override
    public BaseObjectMapper getFullApplicationMapper() {
        return heRecognitionApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<HeRecognitionApplicationDTO> getEvaluationsComponent() {
        return heRecognitionEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_RUDI;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "heRecognitionReceipt.ftl";
    }
}
