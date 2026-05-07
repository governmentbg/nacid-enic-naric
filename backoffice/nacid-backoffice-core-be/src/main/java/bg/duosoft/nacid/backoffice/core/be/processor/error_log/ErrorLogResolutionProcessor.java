package bg.duosoft.nacid.backoffice.core.be.processor.error_log;

import bg.duosoft.nacid.backoffice.core.be.service.common.ErrorLogService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogResolutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacidshared.web.util.json.JsonUtil;
import bg.duosoft.nacidshareddata.exception.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class ErrorLogResolutionProcessor {

    @Autowired
    protected JsonUtil jsonUtil;

    @Autowired
    protected ErrorLogService errorLogService;

    public abstract void process(ErrorLogDTO errorLog);

    protected Integer selectId(ErrorLogDTO errorLog) {
        IntegerIdDTO data = jsonUtil.readJson(errorLog.getDataJson(), IntegerIdDTO.class);
        if (Objects.isNull(data) || Objects.isNull(data.getId())) {
            throw new InternalServerErrorException("[ERROR LOG] Invalid data json! ErrorLogId: " + errorLog.getId());
        }

        return data.getId();
    }

    protected void updateErrorLog(ErrorLogDTO errorLog) {
        LocalDateTime resolvedDate = errorLog.getResolvedDate();
        if (Objects.isNull(resolvedDate)) {
            errorLogService.resolveErrorLog(errorLog.getId(), new ErrorLogResolutionDTO("Automatically resolved !"));
        }
    }

}
