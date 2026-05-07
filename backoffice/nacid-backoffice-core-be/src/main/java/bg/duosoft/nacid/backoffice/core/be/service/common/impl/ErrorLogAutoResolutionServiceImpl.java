package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.core.be.processor.error_log.*;
import bg.duosoft.nacid.backoffice.core.be.service.common.ErrorLogAutoResolutionService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ErrorLogService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ErrorLogType;
import bg.duosoft.nacidshareddata.exception.InternalServerErrorException;
import bg.duosoft.nacidshareddata.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ErrorLogAutoResolutionServiceImpl implements ErrorLogAutoResolutionService {

    private final ErrorLogService service;
    private final AcceptAppFoUpdateProcessor acceptAppFoUpdateProcessor;
    private final AcceptAppPaymentsUpdateProcessor acceptAppPaymentsUpdateProcessor;
    private final DmsAcceptAbdocsRegistrationProcessor dmsAcceptAbdocsRegistrationProcessor;
    private final DmsAcceptFoUpdateProcessor dmsAcceptFoUpdateProcessor;
    private final DmsAcceptReceiptInsertProcessor dmsAcceptReceiptInsertProcessor;

    @Override
    public void resolveAutomatically(Integer id) {
        ErrorLogDTO errorLog = ResponseUtils.notFoundCheck(service.selectById(id));
        ErrorLogResolutionProcessor processor = getProcessor(ErrorLogType.selectByCode(errorLog.getErrorType()));
        processor.process(errorLog);
    }

    public ErrorLogResolutionProcessor getProcessor(ErrorLogType type) {
        switch (type) {
            case UPDATE_FO_APP_DATA_ON_ACCEPT -> {
                return acceptAppFoUpdateProcessor;
            }
            case UPDATE_PAYMENTS_DATA_ON_ACCEPT -> {
                return acceptAppPaymentsUpdateProcessor;
            }
            case DMS_ACCEPT_ABDOCS_REGISTRATION -> {
                return dmsAcceptAbdocsRegistrationProcessor;
            }
            case DMS_ACCEPT_FO_UPDATE -> {
                return dmsAcceptFoUpdateProcessor;
            }
            case DMS_ACCEPT_RECEIPT_INSERT -> {
                return dmsAcceptReceiptInsertProcessor;
            }
            default -> throw new InternalServerErrorException("Unknown ErrorLogType !" + type.code());
        }
    }

}
