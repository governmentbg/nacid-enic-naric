package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.common.application.RevertApplicationStatusToDraftRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.publicaccess.PublicAccessApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.publicaccess.PublicAccessDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.PublicAccessEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.PublicAccessFullEntity;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.PublicAccessEvaluations;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.publicaccess.PublicAccessApplicationMapper;
import bg.duosoft.nacidservicesbe.mapper.publicaccess.PublicAccessDetailsMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.lib.PublicAccessFullRepository;
import bg.duosoft.nacidservicesbe.repository.lib.PublicAccessRepository;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.PublicAccessService;
import bg.duosoft.nacidservicesbe.service.utils.PublicAccessEntityUtils;
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
 * Date: 04.08.2023
 * Time: 15:21
 */
@Service
@RequiredArgsConstructor
public class PublicAccessServiceImpl extends BaseDmsOnlyApplicationServiceImpl<PublicAccessApplicationDTO, CommonApplicantDetailsDTO, PublicAccessDetailsDTO, PublicAccessFullEntity> implements PublicAccessService {

    private final CommonApplicantDetailsMapper commonApplicantDetailsMapper;
    private final PublicAccessApplicationMapper publicAccessApplicationMapper;
    private final PublicAccessDetailsMapper publicAccessDetailsMapper;

    private final PublicAccessFullRepository publicAccessFullRepository;
    private final PublicAccessRepository publicAccessRepository;

    private final PublicAccessEvaluations publicAccessEvaluations;

    @Override
    @Transactional
    public PublicAccessDetailsDTO saveRequestSpecificDetails(Integer applicationId, PublicAccessDetailsDTO publicAccessDetails) {
        PublicAccessFullEntity toCopyFrom = publicAccessDetailsMapper.toEntity(publicAccessDetails);
        PublicAccessFullEntity toSave = publicAccessFullRepository.findById(applicationId).orElseThrow();
        PublicAccessDetailsMapper.copyDetailsToApplication(toSave, toCopyFrom);

        PublicAccessEntityUtils.preSavePublicAccessDetails(toSave);
        PublicAccessFullEntity saved = publicAccessFullRepository.save(toSave);
        PublicAccessDetailsDTO toReturn = publicAccessDetailsMapper.toDto(saved);
        return toReturn;
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(PublicAccessApplicationDTO application) {
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
        return ApplicationSubtype.PUBLIC_ACCESS;
    }

    @Override
    @Transactional
    public void createNewRequest(Integer applicationId) {
        PublicAccessEntity paEntity = new PublicAccessEntity();
        paEntity.setId(applicationId);

        publicAccessRepository.save(paEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PublicAccessFullEntity> prepareEntitiesToSaveOnFileFinalize(Integer mainApplicationId) {
        return Arrays.asList(publicAccessFullRepository.findById(mainApplicationId).orElseThrow());
    }

    @Override
    public BaseObjectMapper<ApplicationEntity, CommonApplicantDetailsDTO> getApplicantDetailsMapper() {
        return commonApplicantDetailsMapper;
    }

    @Override
    public FullApplicationRepositoryBase getFullApplicationRepository() {
        return publicAccessFullRepository;
    }

    @Override
    public BaseObjectMapper getFullApplicationMapper() {
        return publicAccessApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<PublicAccessApplicationDTO> getEvaluationsComponent() {
        return publicAccessEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_LIBRARY;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "publicAccessReceipt.ftl";
    }

    @Override
    public boolean applicationIsReversibleToDraft(RevertApplicationStatusToDraftRequestDTO revertApplicationStatusToDraftRequest) {
        return false;
    }
}
