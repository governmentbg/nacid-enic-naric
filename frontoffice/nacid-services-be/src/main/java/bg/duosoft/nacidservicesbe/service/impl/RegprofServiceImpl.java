package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.common.application.RevertApplicationStatusToDraftRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofEducationDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicationDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofApplicationFullEntity;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofTrainingExperienceEntity;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.RegprofEvaluations;
import bg.duosoft.nacidservicesbe.mapper.regprof.RegprofApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.regprof.RegprofEducationDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.regprof.RegprofApplicationMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.regprof.RegprofApplicationFullRepository;
import bg.duosoft.nacidservicesbe.repository.regprof.RegprofApplicationRepository;
import bg.duosoft.nacidservicesbe.repository.regprof.RegprofTrainingExperienceRepository;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.RegprofService;
import bg.duosoft.nacidservicesbe.service.utils.RegprofTrainingExperienceEntityUtils;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 18:04
 */
@Service
@RequiredArgsConstructor
public class RegprofServiceImpl extends BaseCommonApplicationServiceImpl<RegprofApplicationDTO, RegprofApplicantDetailsDTO, RegprofEducationDetailsDTO, RegprofApplicationFullEntity> implements RegprofService {

    private final RegprofApplicationMapper regprofApplicationMapper;
    private final RegprofApplicantDetailsMapper regprofApplicantDetailsMapper;
    private final RegprofEducationDetailsMapper regprofEducationDetailsMapper;

    private final RegprofApplicationRepository regprofApplicationRepository;
    private final RegprofApplicationFullRepository regprofApplicationFullRepository;
    private final RegprofTrainingExperienceRepository regprofTrainingExperienceRepository;

    private final RegprofEvaluations regprofEvaluations;

    @Override
    @Transactional
    public RegprofEducationDetailsDTO saveRequestSpecificDetails(Integer applicationId, RegprofEducationDetailsDTO educationDetails) {
        RegprofTrainingExperienceEntity toSave = regprofEducationDetailsMapper.toEntity(educationDetails);
        RegprofTrainingExperienceEntity fetchedFromDB = regprofTrainingExperienceRepository.findByRegprofApplicationId(applicationId);

        RegprofTrainingExperienceEntityUtils.keepTrainingExperienceDBDetails(fetchedFromDB, toSave, applicationId);
        RegprofTrainingExperienceEntityUtils.preSaveTrainingExperience(toSave);

        RegprofTrainingExperienceEntity saved = regprofTrainingExperienceRepository.save(toSave);
        RegprofEducationDetailsDTO toReturn = regprofEducationDetailsMapper.toDto(saved);

        toReturn.setServiceType(saveServiceType(applicationId, educationDetails));
        return toReturn;
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(RegprofApplicationDTO application) {
        return createRegprofFeeCalculationParamsMap(application.getEducationDetails() != null && application.getEducationDetails().getServiceType() != null? application.getEducationDetails().getServiceType().getId() : null);
    }

    @Override
    public Map<String, String> createRegprofFeeCalculationParamsMap(String serviceType) {
        Map<String, String> params = new HashMap<>();
        params.put(PaymentServiceImpl.SERVICE_TYPE_PARAM, serviceType);
        return params;
    }

    @Override
    public ApplicationType getInitialApplicationType() {
        return ApplicationType.REGULATED_PROFESSIONS;
    }

    @Override
    public ApplicationSubtype getInitialApplicationSubtype() {
        return ApplicationSubtype.REGULATED_PROFESSIONS;
    }

    @Override
    @Transactional
    public void createNewRequest(Integer applicationId) {
        RegprofApplicationEntity regprofApplication = new RegprofApplicationEntity();
        regprofApplication.setId(applicationId);

        regprofApplicationRepository.save(regprofApplication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegprofApplicationFullEntity> prepareEntitiesToSaveOnFileFinalize(Integer mainApplicationId) {
        return Arrays.asList(regprofApplicationFullRepository.findById(mainApplicationId).orElseThrow());
    }

    @Override
    public BaseObjectMapper<ApplicationEntity, RegprofApplicantDetailsDTO> getApplicantDetailsMapper() {
        return regprofApplicantDetailsMapper;
    }

    @Override
    public FullApplicationRepositoryBase getFullApplicationRepository() {
        return regprofApplicationFullRepository;
    }

    @Override
    public BaseObjectMapper getFullApplicationMapper() {
        return regprofApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<RegprofApplicationDTO> getEvaluationsComponent() {
        return regprofEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_REGPROF;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "regprofReceipt.ftl";
    }

    @Override
    public boolean applicationIsReversibleToDraft(RevertApplicationStatusToDraftRequestDTO revertApplicationStatusToDraftRequest) {
        Integer apostilleFlag = regprofApplicationFullRepository.getApostilleApplicationFlag(revertApplicationStatusToDraftRequest.getApplicationId());
        return  apostilleFlag == null || apostilleFlag.equals(0);
    }
}
