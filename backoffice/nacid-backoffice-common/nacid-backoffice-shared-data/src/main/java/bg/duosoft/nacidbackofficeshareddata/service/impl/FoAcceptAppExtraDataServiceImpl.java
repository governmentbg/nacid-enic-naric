package bg.duosoft.nacidbackofficeshareddata.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationProperty;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ErrorLogType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationPropertyDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import bg.duosoft.nacid.payments.client.client.liabilities.AdminLiabilitiesClient;
import bg.duosoft.nacidbackofficeshareddata.service.BaseApplicationPropertyService;
import bg.duosoft.nacidbackofficeshareddata.service.BaseErrorLogService;
import bg.duosoft.nacidbackofficeshareddata.service.FoAcceptAppExtraDataService;
import bg.duosoft.nacidfrontofficedto.services.common.application.AcceptApplicationRequestDTO;
import bg.duosoft.nacidservicesclient.client.ServicesBoApiClient;
import bg.duosoft.nacidshared.web.util.json.JsonUtil;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoAcceptAppExtraDataServiceImpl implements FoAcceptAppExtraDataService {

    private final ServicesBoApiClient servicesBoApiClient;
    private final BaseErrorLogService baseErrorLogService;
    private final BaseApplicationPropertyService baseConfigParamService;
    private final JsonUtil jsonUtil;
    private final AdminLiabilitiesClient adminLiabilitiesClient;


    @Override
    public void updateRegprofFrontOfficeData(ApplicationDTO application, boolean isApostille) {
        Consumer<AcceptApplicationRequestDTO> consumer = servicesBoApiClient::acceptApplication;
        if (isApostille) {
            consumer = servicesBoApiClient::acceptRegprofApostilleApplication;
        }
        executeUpdateFrontOfficeData(application, consumer);
    }

    @Override
    public void updateFrontOfficeData(AcceptApplicationRequestDTO acceptApplicationRequest) {
        Consumer<AcceptApplicationRequestDTO> consumer = servicesBoApiClient::acceptApplication;

        try {
            consumer.accept(acceptApplicationRequest);
        } catch (Exception e) {
            String message = "Cannot update front-office additional documents application data after bo acceptance ! : Frontoffice ID: " + acceptApplicationRequest.getApplicationId();
            log.error(message);
            log.error(e.getMessage(), e);

            try {
                String dataJson = jsonUtil.createJson(new IntegerIdDTO(acceptApplicationRequest.getApplicationId()));
                baseErrorLogService.insert(ErrorLogType.UPDATE_FO_APP_DATA_ON_ACCEPT, message, dataJson);
            } catch (Exception ex) {
                log.error("[ERROR LOG INSERT] Cannot insert error log record of type {} ! Frontoffice ID: {}", ErrorLogType.UPDATE_FO_APP_DATA_ON_ACCEPT.code(), acceptApplicationRequest.getApplicationId());
                log.error(ex.getMessage(), ex);
            }
        }
    }

    @Override
    public void updateFrontOfficeData(ApplicationDTO application) {
        Consumer<AcceptApplicationRequestDTO> consumer = servicesBoApiClient::acceptApplication;
        executeUpdateFrontOfficeData(application, consumer);
    }

    private void executeUpdateFrontOfficeData(ApplicationDTO application, Consumer<AcceptApplicationRequestDTO> acceptAppConsumer) {
        Integer applicationId = application.getId();

        try {
            AcceptApplicationRequestDTO requestDTO = new AcceptApplicationRequestDTO();
            requestDTO.setApplicationId(application.getEfilingId());
            requestDTO.setInitiatingUser(SecurityUtils.getUsername());

            acceptAppConsumer.accept(requestDTO);
        } catch (Exception e) {
            log.error("Cannot update front-office application data after bo acceptance ! Backoffice ID: {}", applicationId);
            log.error(e.getMessage(), e);

            try {
                String message = getErrorLogMessage(application, ApplicationProperty.ERROR_LOG_MSG_UPDATE_FO_APP_DATA_ON_ACCEPT);
                String dataJson = jsonUtil.createJson(new IntegerIdDTO(applicationId));
                baseErrorLogService.insert(ErrorLogType.UPDATE_FO_APP_DATA_ON_ACCEPT, message, dataJson);
            } catch (Exception ex) {
                log.error("[ERROR LOG INSERT] Cannot insert error log record of type {} ! Backoffice ID: {}", ErrorLogType.UPDATE_FO_APP_DATA_ON_ACCEPT.code(), applicationId);
                log.error(ex.getMessage(), ex);
            }
        }
    }

    private String getErrorLogMessage(ApplicationDTO application, ApplicationProperty applicationProperty) {
        try {
            ApplicationPropertyDTO property = baseConfigParamService.selectByType(applicationProperty);
            if (Objects.nonNull(property) && StringUtils.hasText(property.getValue())) {
                return property.getValue()
                        .replace("{0}", String.valueOf(application.getEfilingId()))
                        .replace("{1}", String.valueOf(application.getId()))
                        .replace("{2}", AbdocsNumbersUtils.buildRegistrationNumber(application.getEntryNumber(), application.getEntryDate()));
            }
        } catch (Exception em) {
            log.error(em.getMessage());
        }

        return applicationProperty.code();
    }

    public void updatePaymentsData(ApplicationDTO application) {
        Integer applicationId = application.getId();
        Integer efilingId = application.getEfilingId();
        String entryNumber = application.getEntryNumber();
        LocalDate entryDate = application.getEntryDate();

        try {
            adminLiabilitiesClient.updateBackofficeNumber(AbdocsNumbersUtils.buildRegistrationNumber(entryNumber, entryDate), AbdocsNumbersUtils.buildRegistrationNumber(entryNumber, entryDate));
        } catch (Exception e) {
            log.error("Cannot update payments backoffice number ! Back-office ID: {}, Front-office ID: {}", applicationId, efilingId);
            log.error(e.getMessage(), e);
            try {
                String message = getErrorLogMessage(application, ApplicationProperty.ERROR_LOG_MSG_UPDATE_PAYMENTS_DATA_ON_ACCEPT);
                String dataJson = jsonUtil.createJson(new IntegerIdDTO(applicationId));
                baseErrorLogService.insert(ErrorLogType.UPDATE_PAYMENTS_DATA_ON_ACCEPT, message, dataJson);
            } catch (Exception ex) {
                log.error("[ERROR LOG INSERT] Cannot insert error log record of type {} ! Backoffice ID: {}", ErrorLogType.UPDATE_PAYMENTS_DATA_ON_ACCEPT.code(), applicationId);
                log.error(ex.getMessage(), ex);
            }
        }
    }
}
