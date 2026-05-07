package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacidshared.web.repository.BaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ContentManagementEntity;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface ContentManagementRepository extends BaseRepository<ContentManagementEntity, String> {
    @Query(value = "select data from common.content_management where id = ?1 and active = true", nativeQuery = true)
    String findDataByIdAndActive(String id);

    @Query(value = "select * from common.content_management where type = ?1 and active = true order by content_order asc", nativeQuery = true)
    List<ContentManagementEntity> findByTypeAndActive(String type);
}
