package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;

import java.util.List;

public interface ErrorLogService {

    ErrorLogDTO resolveErrorLog(Integer id, ErrorLogResolutionDTO resolutionDto);

    ErrorLogDTO selectById(Integer id);

    List<ErrorLogDTO> searchRecords(ErrorLogFilterDTO id);

    int getRecordsCount(ErrorLogFilterDTO id);

    Integer selectUnresolvedCount();

    List<Integer> selectUnresolvedIdentifiers();

}
