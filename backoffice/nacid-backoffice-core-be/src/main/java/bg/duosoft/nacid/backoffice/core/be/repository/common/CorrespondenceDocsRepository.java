package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.CorrespondenceDocsEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CorrespondenceDocsRepository extends BaseRepository<CorrespondenceDocsEntity, Integer> {

    @Query("select p from CorrespondenceDocsEntity p where p.foSendDate is null and p.dateCreated > :dateLimit order by p.dateCreated asc")
    List<CorrespondenceDocsEntity> selectValidCorrespondenceDocs(@Param("dateLimit") LocalDateTime dateLimit);

}
