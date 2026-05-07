package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ErrorLogType;

public interface BaseErrorLogService {

    ErrorLogDTO insert(ErrorLogType type, String errorMessage, String dataJSON);

    ErrorLogDTO insert(ErrorLogType type, String errorMessage, String dataJSON, String referenceId);

    ErrorLogDTO resolve(Integer id, String resolvedComment, String resolvedUser);

    ErrorLogDTO selectByReferenceIdAndType(ErrorLogType type, String referenceId);

}
