package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AttachmentEntity;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionCalendarEntity;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.CommissionCalendarRepositoryCustom;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommissionCalendarRepository extends BaseRepository<CommissionCalendarEntity, Integer>, CommissionCalendarRepositoryCustom {

    @Query(value = "select cc.session_num from rudi.commission_applications ca " +
            " inner join rudi.commission_calendar cc on cc.id = ca.calendar_id where ca.apn_id = :applicationId order by cc.date_created desc limit 1", nativeQuery = true)
    Integer selectLastCommissionSessionNumByApnId(@Param("applicationId") Integer applicationId);

    @Query(value = "select max(session_num) from rudi.commission_calendar cc", nativeQuery = true)
    Integer getMaxSessionNum();

    @Query("SELECT u.commissionProtocol FROM CommissionCalendarEntity u where u.id = :calendarId")
    AttachmentEntity getCommissionCalendarProtocol(@Param("calendarId") Integer calendarId);

    @Query("SELECT u.secretary FROM CommissionCalendarEntity u where u.id = :calendarId")
    String getSecretary(@Param("calendarId") Integer calendarId);
}
