package bg.duosoft.nacid.backoffice.core.be.processor.error_log;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.DmsOnlyFoReceiptSaveDTO;
import bg.duosoft.nacid.backoffice.libserv.client.client.app.AdminDmsOnlyAcceptClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DmsAcceptReceiptInsertProcessor extends ErrorLogResolutionProcessor {

    private final AdminDmsOnlyAcceptClient adminDmsOnlyAcceptClient;

    @Override
    public void process(ErrorLogDTO errorLog) {
        String dataJson = errorLog.getDataJson();
        DmsOnlyFoReceiptSaveDTO receiptSaveDTO = jsonUtil.readJson(dataJson, DmsOnlyFoReceiptSaveDTO.class);
        if (Objects.nonNull(receiptSaveDTO)) {
            adminDmsOnlyAcceptClient.errorLogSaveFoReceipt(receiptSaveDTO);
            updateErrorLog(errorLog);
        }
    }

}
