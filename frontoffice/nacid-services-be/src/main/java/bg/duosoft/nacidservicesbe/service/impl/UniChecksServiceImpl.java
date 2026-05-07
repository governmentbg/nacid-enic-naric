package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.person.ApplicantType;
import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiApplicationFullEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiSarApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseEntity;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.UniChecksEvaluations;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.unichecks.SarApplicationMapper;
import bg.duosoft.nacidservicesbe.mapper.unichecks.UniChecksApplicationMapper;
import bg.duosoft.nacidservicesbe.mapper.unichecks.UniChecksEducationDetailsMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.rudi.RudiApplicationFullRepository;
import bg.duosoft.nacidservicesbe.repository.rudi.RudiApplicationRepository;
import bg.duosoft.nacidservicesbe.repository.rudi.RudiSarApplicationRepository;
import bg.duosoft.nacidservicesbe.repository.rudi.RudiTrainingCourseRepository;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.UniChecksService;
import bg.duosoft.nacidservicesbe.service.utils.TrainingCourseEntityUtils;
import bg.duosoft.nacidservicesbe.utils.PaymentUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 15:37
 */
@Service
@RequiredArgsConstructor
public class UniChecksServiceImpl extends BaseCommonApplicationServiceImpl<UniChecksApplicationDTO, CommonApplicantDetailsDTO, UniChecksEducationDetailsDTO, RudiApplicationFullEntity> implements UniChecksService {

    private final CommonApplicantDetailsMapper commonApplicantDetailsMapper;
    private final UniChecksEducationDetailsMapper uniChecksEducationDetailsMapper;
    private final SarApplicationMapper sarApplicationMapper;

    private final UniChecksApplicationMapper uniChecksApplicationMapper;

    private final RudiApplicationRepository rudiApplicationRepository;
    private final RudiSarApplicationRepository rudiSarApplicationRepository;
    private final RudiApplicationFullRepository rudiApplicationFullRepository;
    private final RudiTrainingCourseRepository rudiTrainingCourseRepository;

    private final UniChecksEvaluations uniChecksEvaluations;

    @Override
    @Transactional
    public UniChecksEducationDetailsDTO saveRequestSpecificDetails(Integer applicationId, UniChecksEducationDetailsDTO educationDetails) {
        RudiTrainingCourseEntity toSave = uniChecksEducationDetailsMapper.toEntity(educationDetails);

        RudiTrainingCourseEntity fetchedFromDB = rudiTrainingCourseRepository.findByRudiApplicationId(applicationId);
        TrainingCourseEntityUtils.keepTrainingCourseDBDetails(fetchedFromDB, toSave, applicationId);
        TrainingCourseEntityUtils.preSaveTrainingCourse(toSave);

        RudiSarApplicationEntity sarToSave = sarApplicationMapper.toEntity(educationDetails);
        sarToSave.setRudiApplicationId(applicationId);

        RudiTrainingCourseEntity saved = rudiTrainingCourseRepository.save(toSave);
        RudiSarApplicationEntity savedSar = rudiSarApplicationRepository.save(sarToSave);

        UniChecksEducationDetailsDTO toReturn = uniChecksEducationDetailsMapper.toDto(saved);
        UniChecksEducationDetailsDTO toReturnSar = sarApplicationMapper.toDto(savedSar);
        SarApplicationMapper.setSarDetailsToMainEducationDetailsDto(toReturn, toReturnSar);

        toReturn.setServiceType(saveServiceType(applicationId, educationDetails));
        return toReturn;
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(UniChecksApplicationDTO application) {
        ApplicantType applicantType = application.getApplicantDetails().getApplicant().getApplicantType();
        Boolean statute = Boolean.TRUE.equals(application.getEducationDetails().getStatute());
        Boolean authenticity = Boolean.TRUE.equals(application.getEducationDetails().getAuthenticity());
        Boolean recommendation = Boolean.TRUE.equals(application.getEducationDetails().getRecommendation());
        String serviceType = application.getEducationDetails().getServiceType() != null ? application.getEducationDetails().getServiceType().getId() : null;

        return createUniChecksFeeCalculationParamsMap(statute, authenticity, recommendation, serviceType, applicantType);
    }

    @Override
    public Map<String, String> createUniChecksFeeCalculationParamsMap(Boolean statute, Boolean authenticity, Boolean recommendation, String serviceType, ApplicantType applicantType) {
        Map<String, String> params = new HashMap<>();
        params.put(PaymentService.APPLICATION_SUBTYPE_PARAM, getInitialApplicationSubtype().getCode());
        params.put(PaymentService.LEGAL_TYPE_PARAM, applicantType.getLegalTypeCode());
        if(applicantType.getLegalNatureTypeCode() != null){
            params.put(PaymentService.LEGAL_NATURE_TYPE_PARAM, applicantType.getLegalNatureTypeCode());
        }
        params.put(PaymentService.SERVICE_TYPE_PARAM, serviceType);
        params.put(PaymentService.STATUTE_FLAG_PARAM, statute.toString());
        params.put(PaymentService.AUTHENTICITY_FLAG_PARAM, authenticity.toString());
        params.put(PaymentService.RECOMMENDATION_FLAG_PARAM, recommendation.toString());

        return params;
    }

    @Override
    public String getPayerName(UniChecksApplicationDTO app){
        return PaymentUtils.createUniChecksPayerName(app);
    }

    @Override
    public ApplicationType getInitialApplicationType() {
        return ApplicationType.ACADEMIC_RECOGNITION;
    }

    @Override
    public ApplicationSubtype getInitialApplicationSubtype() {
        return ApplicationSubtype.UNI_CHECKS;
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
    public BaseObjectMapper<ApplicationEntity, CommonApplicantDetailsDTO> getApplicantDetailsMapper() {
        return commonApplicantDetailsMapper;
    }

    @Override
    public FullApplicationRepositoryBase getFullApplicationRepository() {
        return rudiApplicationFullRepository;
    }

    @Override
    public BaseObjectMapper getFullApplicationMapper() {
        return uniChecksApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<UniChecksApplicationDTO> getEvaluationsComponent() {
        return uniChecksEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_RUDI;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "uniChecksReceipt.ftl";
    }
}
