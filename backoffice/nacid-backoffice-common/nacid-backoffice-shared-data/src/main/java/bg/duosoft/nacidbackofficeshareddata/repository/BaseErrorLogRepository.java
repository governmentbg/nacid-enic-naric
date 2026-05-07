package bg.duosoft.nacidbackofficeshareddata.repository;


import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ErrorLogEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ErrorLogType;

public interface BaseErrorLogRepository {

    ErrorLogEntity insertRecord(ErrorLogEntity entity);

    ErrorLogEntity resolveRecord(ErrorLogEntity entity);

    ErrorLogEntity selectById(Integer id);

    ErrorLogEntity selectByReferenceIdAndType(ErrorLogType type, String referenceId);

}
