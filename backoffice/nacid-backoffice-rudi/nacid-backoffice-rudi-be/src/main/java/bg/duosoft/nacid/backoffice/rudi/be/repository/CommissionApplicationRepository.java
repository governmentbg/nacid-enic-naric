package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionApplicationEntity;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.CommissionApplicationRepositoryCustom;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface CommissionApplicationRepository extends BaseRepository<CommissionApplicationEntity, Integer>, CommissionApplicationRepositoryCustom {
    @Query("SELECT e FROM CommissionApplicationEntity e WHERE e.calendarId = :calendarId and e.applicationId = :applicationId")
    CommissionApplicationEntity selectByCalendarAndApplicationId(@Param("calendarId") Integer calendarId, @Param("applicationId") Integer applicationId);

    @Query("SELECT e FROM CommissionApplicationEntity e WHERE e.applicationId = :applicationId")
    List<CommissionApplicationEntity> selectByApplicationId(@Param("applicationId") Integer applicationId);

    @Query("SELECT e FROM CommissionApplicationEntity e WHERE e.calendarId = :calendarId")
    List<CommissionApplicationEntity> selectByCalendarId(@Param("calendarId") Integer calendarId);
}
