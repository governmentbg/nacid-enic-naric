package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationCertificatesEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationAttachedDocRepository extends BaseRepository<ApplicationAttachedDocEntity, Integer> {
    @Query("SELECT a.docflowId from ApplicationAttachedDocEntity a where a.id = :id")
    String selectDocflowIdById(@Param("id") Integer id);
}
