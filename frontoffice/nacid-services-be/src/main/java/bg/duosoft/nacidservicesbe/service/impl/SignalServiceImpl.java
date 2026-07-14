package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.common.application.RevertApplicationStatusToDraftRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.signal.SignalApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.signal.SignalDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.SignalEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.SignalFullEntity;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.SignalEvaluations;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.signal.SignalApplicationMapper;
import bg.duosoft.nacidservicesbe.mapper.signal.SignalDetailsMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.lib.SignalFullRepository;
import bg.duosoft.nacidservicesbe.repository.lib.SignalRepository;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.SignalService;
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
 * Date: 06.03.2023
 * Time: 16:54
 */
@Service
@RequiredArgsConstructor
public class SignalServiceImpl extends BaseDmsOnlyApplicationServiceImpl<SignalApplicationDTO, CommonApplicantDetailsDTO, SignalDetailsDTO, SignalFullEntity> implements SignalService {

    private final CommonApplicantDetailsMapper commonApplicantDetailsMapper;
    private final SignalApplicationMapper signalApplicationMapper;
    private final SignalDetailsMapper signalDetailsMapper;

    private final SignalFullRepository signalFullRepository;
    private final SignalRepository signalRepository;

    private final SignalEvaluations signalEvaluations;

    @Override
    @Transactional
    public SignalDetailsDTO saveRequestSpecificDetails(Integer applicationId, SignalDetailsDTO signalDetails) {
        SignalFullEntity toCopyFrom = signalDetailsMapper.toEntity(signalDetails);
        SignalFullEntity toSave = signalFullRepository.findById(applicationId).orElseThrow();
        SignalDetailsMapper.copyDetailsToApplication(toSave, toCopyFrom);

        SignalFullEntity saved = signalFullRepository.save(toSave);
        SignalDetailsDTO toReturn = signalDetailsMapper.toDto(saved);
        return toReturn;
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(SignalApplicationDTO application) {
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
        return ApplicationSubtype.SIGNAL;
    }

    @Override
    @Transactional
    public void createNewRequest(Integer applicationId) {
        SignalEntity signalEntity = new SignalEntity();
        signalEntity.setId(applicationId);

        signalRepository.save(signalEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SignalFullEntity> prepareEntitiesToSaveOnFileFinalize(Integer mainApplicationId) {
        return Arrays.asList(signalFullRepository.findById(mainApplicationId).orElseThrow());
    }

    @Override
    public BaseObjectMapper<ApplicationEntity, CommonApplicantDetailsDTO> getApplicantDetailsMapper() {
        return commonApplicantDetailsMapper;
    }

    @Override
    public FullApplicationRepositoryBase getFullApplicationRepository() {
        return signalFullRepository;
    }

    @Override
    public BaseObjectMapper getFullApplicationMapper() {
        return signalApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<SignalApplicationDTO> getEvaluationsComponent() {
        return signalEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_LIBRARY;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "signalReceipt.ftl";
    }

    @Override
    public boolean applicationIsReversibleToDraft(RevertApplicationStatusToDraftRequestDTO revertApplicationStatusToDraftRequest) {
        return false;
    }
}
