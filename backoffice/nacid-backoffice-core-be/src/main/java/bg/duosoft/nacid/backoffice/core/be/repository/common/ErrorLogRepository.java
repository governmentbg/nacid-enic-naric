package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacid.backoffice.core.be.repository.common.custom.ErrorLogSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ErrorLogEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ErrorLogRepository extends BaseRepository<ErrorLogEntity, Integer>, ErrorLogSearchRepository {

    @Query("SELECT r.id from ErrorLogEntity r where r.resolvedDate IS NULL")
    List<Integer> selectUnresolvedIdentifiers();

}
