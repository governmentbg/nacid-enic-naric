package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionMemberEntity;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.CommissionMemberRepositoryCustom;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommissionMemberRepository extends BaseRepository<CommissionMemberEntity, Integer>, CommissionMemberRepositoryCustom {

    @Query("SELECT r.id from CommissionMemberEntity r where r.id in (SELECT c.commissionMember.id from CommissionParticipationEntity c where c.calendar.id =:calendarId)")
    List<Integer> selectAllCommissionMemberIdsByCalendarId(@Param("calendarId") Integer calendarId);


    @Query("SELECT r from CommissionMemberEntity r where r.id in (:ids)")
    List<CommissionMemberEntity> selectMembersByIds(List<Integer> ids);

    @Query("SELECT r from CommissionMemberEntity r where r.commissionPosition.pk.id = :position ")
    List<CommissionMemberEntity> selectMembersByPosition(String position);
}
