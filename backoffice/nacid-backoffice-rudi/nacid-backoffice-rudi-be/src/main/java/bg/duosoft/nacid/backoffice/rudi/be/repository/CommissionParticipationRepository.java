package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionParticipationEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommissionParticipationRepository extends BaseRepository<CommissionParticipationEntity, Integer> {

    @Query("SELECT e FROM CommissionParticipationEntity e WHERE e.calendar.id = :calendarId and e.commissionMember.id = :memberId")
    CommissionParticipationEntity selectByCalendarAndMemberId(@Param("calendarId") Integer calendarId, @Param("memberId") Integer memberId);
    @Query("SELECT e FROM CommissionParticipationEntity e WHERE e.calendar.id = :calendarId order by e.id asc ")
    List<CommissionParticipationEntity> selectByCalendarId(@Param("calendarId") Integer calendarId);

}
