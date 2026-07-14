package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocDegreesApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocEducationDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiApplicationFullEntity;
import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseEntity;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.DocDegreeEvaluations;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.RudiApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.docdegrees.DocDegreesApplicationMapper;
import bg.duosoft.nacidservicesbe.mapper.docdegrees.DocEducationDetailsMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.rudi.RudiApplicationFullRepository;
import bg.duosoft.nacidservicesbe.repository.rudi.RudiApplicationRepository;
import bg.duosoft.nacidservicesbe.repository.rudi.RudiTrainingCourseRepository;
import bg.duosoft.nacidservicesbe.service.DocDegreesService;
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
 * Date: 20.01.2023
 * Time: 17:33
 */
@Service
@RequiredArgsConstructor
public class DocDegreesServiceImpl extends BaseCommonApplicationServiceImpl<DocDegreesApplicationDTO, RudiApplicantDetailsDTO, DocEducationDetailsDTO, RudiApplicationFullEntity> implements DocDegreesService {

    private final RudiApplicantDetailsMapper rudiApplicantDetailsMapper;
    private final DocEducationDetailsMapper docEducationDetailsMapper;

    private final DocDegreesApplicationMapper docDegreesApplicationMapper;

    private final RudiApplicationRepository rudiApplicationRepository;
    private final RudiApplicationFullRepository rudiApplicationFullRepository;
    private final RudiTrainingCourseRepository rudiTrainingCourseRepository;

    private final DocDegreeEvaluations docDegreeEvaluations;

    @Override
    @Transactional
    public DocEducationDetailsDTO saveRequestSpecificDetails(Integer applicationId, DocEducationDetailsDTO educationDetails) {
        RudiTrainingCourseEntity toSave = docEducationDetailsMapper.toEntity(educationDetails);

        RudiTrainingCourseEntity fetchedFromDB = rudiTrainingCourseRepository.findByRudiApplicationId(applicationId);
        TrainingCourseEntityUtils.keepTrainingCourseDBDetails(fetchedFromDB, toSave, applicationId);
        TrainingCourseEntityUtils.preSaveTrainingCourse(toSave);

        RudiTrainingCourseEntity saved = rudiTrainingCourseRepository.save(toSave);
        DocEducationDetailsDTO toReturn = docEducationDetailsMapper.toDto(saved);
        return toReturn;
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(DocDegreesApplicationDTO application) {
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
        return ApplicationSubtype.DOC_DEGREES;
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
        return docDegreesApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<DocDegreesApplicationDTO> getEvaluationsComponent() {
        return docDegreeEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_RUDI;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "docDegreesReceipt.ftl";
    }
}
