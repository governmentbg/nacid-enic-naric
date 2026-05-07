package bg.duosoft.nacid.backoffice.core.be.processor.error_log;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogDTO;
import bg.duosoft.nacid.backoffice.core.data.util.abdocs.AbdocsNumbersUtils;
import bg.duosoft.nacid.payments.client.client.liabilities.AdminLiabilitiesClient;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AcceptAppPaymentsUpdateProcessor extends ApplicationErrorLogResolutionProcessor {

    private final AdminLiabilitiesClient adminLiabilitiesClient;

    @Override
    public void process(ErrorLogDTO errorLog) {
        ApplicationDTO boApplication = selectBoApp(errorLog, selectId(errorLog));
        CommonApplicationDTO foApplication = selectFoApp(errorLog, boApplication);
        adminLiabilitiesClient.updateBackofficeNumber(foApplication.getTempNumber(), AbdocsNumbersUtils.buildRegistrationNumber(boApplication.getEntryNumber(), boApplication.getEntryDate()));
        updateErrorLog(errorLog);
    }

}
