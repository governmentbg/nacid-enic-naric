package bg.duosoft.nacid.backoffice.core.be.repository.common.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ChangeRecordLogEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ChangeRecordLogFilterDTO;

import java.util.List;

public interface ChangeRecordLogSearchRepository {
    List<ChangeRecordLogEntity> selectByApplicationName(String applicationName, Integer page, Integer pageSize);

    List<ChangeRecordLogEntity> searchRecords(ChangeRecordLogFilterDTO filter);

    int getRecordsCount(ChangeRecordLogFilterDTO filter);

    List<List<Object>> selectServiceDictionary(String applicationName);
}
