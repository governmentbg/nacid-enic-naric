package bg.duosoft.nacid.backoffice.core.be.repository.common.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ErrorLogEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ErrorLogFilterDTO;

import java.util.List;

public interface ErrorLogSearchRepository {

    List<ErrorLogEntity> searchRecords(ErrorLogFilterDTO filter);

    int getRecordsCount(ErrorLogFilterDTO filter);

}
