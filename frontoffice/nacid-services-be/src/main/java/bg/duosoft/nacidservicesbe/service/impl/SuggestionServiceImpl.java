package bg.duosoft.nacidservicesbe.service.impl;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.common.application.RevertApplicationStatusToDraftRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.suggestion.SuggestionApplicationDTO;
import bg.duosoft.nacidfrontofficedto.services.suggestion.SuggestionDetailsDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import bg.duosoft.nacidservicesbe.domain.entity.lib.*;
import bg.duosoft.nacidservicesbe.evaluations.BaseApplicationEvaluations;
import bg.duosoft.nacidservicesbe.evaluations.SuggestionEvaluations;
import bg.duosoft.nacidservicesbe.mapper.common.applicantdetails.CommonApplicantDetailsMapper;
import bg.duosoft.nacidservicesbe.mapper.suggestion.SuggestionApplicationMapper;
import bg.duosoft.nacidservicesbe.repository.base.FullApplicationRepositoryBase;
import bg.duosoft.nacidservicesbe.repository.lib.SuggestionFullRepository;
import bg.duosoft.nacidservicesbe.repository.lib.SuggestionRepository;
import bg.duosoft.nacidservicesbe.service.PaymentService;
import bg.duosoft.nacidservicesbe.service.SuggestionService;
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
public class SuggestionServiceImpl extends BaseDmsOnlyApplicationServiceImpl<SuggestionApplicationDTO, CommonApplicantDetailsDTO, SuggestionDetailsDTO, SuggestionFullEntity> implements SuggestionService {

    private final CommonApplicantDetailsMapper commonApplicantDetailsMapper;
    private final SuggestionApplicationMapper suggestionApplicationMapper;

    private final SuggestionFullRepository suggestionFullRepository;
    private final SuggestionRepository suggestionRepository;

    private final SuggestionEvaluations suggestionEvaluations;

    @Override
    @Transactional
    public SuggestionDetailsDTO saveRequestSpecificDetails(Integer applicationId, SuggestionDetailsDTO suggestionDetailsDTO) {
       SuggestionFullEntity toSave = suggestionFullRepository.findById(applicationId).orElseThrow();
       toSave.setSuggestionText(suggestionDetailsDTO.getSuggestion());
       SuggestionFullEntity saved = suggestionFullRepository.save(toSave);
       SuggestionDetailsDTO toReturn = new SuggestionDetailsDTO();
       toReturn.setSuggestion(saved.getSuggestionText());
       return toReturn;
    }

    @Override
    public Map<String, String> createFeeCalculationParamsMap(SuggestionApplicationDTO application) {
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
        return ApplicationSubtype.SUGGESTION;
    }

    @Override
    @Transactional
    public void createNewRequest(Integer applicationId) {
        SuggestionEntity suggestionEntity = new SuggestionEntity();
        suggestionEntity.setId(applicationId);

        suggestionRepository.save(suggestionEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SuggestionFullEntity> prepareEntitiesToSaveOnFileFinalize(Integer mainApplicationId) {
        return Arrays.asList(suggestionFullRepository.findById(mainApplicationId).orElseThrow());
    }

    @Override
    public BaseObjectMapper<ApplicationEntity, CommonApplicantDetailsDTO> getApplicantDetailsMapper() {
        return commonApplicantDetailsMapper;
    }

    @Override
    public FullApplicationRepositoryBase getFullApplicationRepository() {
        return suggestionFullRepository;
    }

    @Override
    public BaseObjectMapper getFullApplicationMapper() {
        return suggestionApplicationMapper;
    }

    @Override
    public BaseApplicationEvaluations<SuggestionApplicationDTO> getEvaluationsComponent() {
        return suggestionEvaluations;
    }

    @Override
    public String getPaymentModule() {
        return PaymentService.PAYMENT_MODULE_LIBRARY;
    }

    @Override
    public String getApplicationReceiptTemplateName() {
        return "suggestionReceipt.ftl";
    }

    @Override
    public boolean applicationIsReversibleToDraft(RevertApplicationStatusToDraftRequestDTO revertApplicationStatusToDraftRequest) {
        return false;
    }
}
