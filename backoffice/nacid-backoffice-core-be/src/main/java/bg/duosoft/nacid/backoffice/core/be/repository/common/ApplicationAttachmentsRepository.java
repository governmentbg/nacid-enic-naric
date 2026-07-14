package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationAttachmentsRepository extends BaseRepository<ApplicationAttachedDocEntity, Integer> {
    @Query(value = "SELECT c from ApplicationAttachedDocEntity c where c.application.id = :applicationId and (:direction is null or c.documentType.direction = :direction) and c.docCategory.pk.id = :docCategory")
    List<ApplicationAttachedDocEntity> selectByApplicationIdAndDirection(@Param("applicationId") Integer applicationId, @Param("direction") String direction, @Param("docCategory") String docCategory);

}
