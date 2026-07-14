package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacid.backoffice.core.be.repository.common.custom.ChangeRecordLogSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ChangeRecordLogEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;

public interface ChangeRecordLogRepository extends BaseRepository<ChangeRecordLogEntity, Integer>, ChangeRecordLogSearchRepository {
}
