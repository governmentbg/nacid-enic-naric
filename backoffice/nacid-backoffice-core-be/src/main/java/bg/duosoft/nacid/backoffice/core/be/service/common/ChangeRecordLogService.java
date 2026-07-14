package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.base.StringKeyNomenclatureBase;

import java.util.List;

public interface ChangeRecordLogService {
    ChangeRecordLogDTO selectById(Integer id);

    List<ChangeRecordLogSimpleDTO> selectByApplicationName(String applicationName, Integer page, Integer pageSize);

    List<ChangeRecordLogSimpleDTO> searchRecords(ChangeRecordLogFilterDTO id);

    int getRecordsCount(ChangeRecordLogFilterDTO id);

    List<StringKeyNomenclatureBase> selectServiceDictionary(String applicationName);
}
