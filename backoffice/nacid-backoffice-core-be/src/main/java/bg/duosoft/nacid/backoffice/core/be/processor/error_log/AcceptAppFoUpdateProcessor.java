package bg.duosoft.nacid.backoffice.core.be.processor.error_log;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.AcceptApplicationRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import bg.duosoft.nacidservicesclient.client.ServicesBoApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class AcceptAppFoUpdateProcessor extends ApplicationErrorLogResolutionProcessor {

    private final ServicesBoApiClient servicesBoApiClient;

    @Override
    public void process(ErrorLogDTO errorLog) {
        ApplicationDTO boApplication = selectBoApp(errorLog, selectId(errorLog));
        CommonApplicationDTO foApplication = selectFoApp(errorLog, boApplication);
        if (!StringUtils.hasText(foApplication.getEntryNumber())) {
            LocalDate boEntryDate = boApplication.getEntryDate();
            String boEntryNumber = boApplication.getEntryNumber();

            AcceptApplicationRequestDTO requestDTO = new AcceptApplicationRequestDTO();
            requestDTO.setApplicationId(foApplication.getId());
            requestDTO.setEntryNumber(boEntryNumber);
            requestDTO.setEntryDate(boEntryDate);
            requestDTO.setInitiatingUser(boApplication.getUserCreated());

            servicesBoApiClient.acceptApplication(requestDTO);
        }

        updateErrorLog(errorLog);
    }

}
