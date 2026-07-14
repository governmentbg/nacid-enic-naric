package bg.duosoft.nacidcoreapi.repository.common;

import bg.duosoft.nacidcoredata.domain.entity.ContentManagementEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ContentManagementRepository extends org.springframework.data.jpa.repository.JpaRepository<ContentManagementEntity, String> {
    @Query(value = "select data from common.content_management where id = ?1 and active = true", nativeQuery = true)
    String findDataByIdAndActive(String id);

    @Query(value = "select * from common.content_management where type = ?1 and active = true order by content_order asc", nativeQuery = true)
    List<ContentManagementEntity> findByTypeAndActive(String type);
}
