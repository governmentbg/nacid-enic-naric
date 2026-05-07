package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiApplicationsEntity;
import bg.duosoft.nacid.backoffice.rudi.be.repository.custom.ApplicationsRepositoryCustom;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationsRepository extends BaseRepository<VRudiApplicationsEntity, Integer>, ApplicationsRepositoryCustom {
    @Query("SELECT r.id from VRudiApplicationsEntity r where r.id in (SELECT c.applicationId from CommissionApplicationEntity c where c.calendarId =:calendarId)")
    List<Integer> selectAllApplicationIdsByCalendarId(@Param("calendarId") Integer calendarId);

    @Query("SELECT r from VRudiApplicationsEntity r where r.id in (:ids)")
    List<VRudiApplicationsEntity> selectApplicationsByIds(List<Integer> ids);

    @Query("SELECT r from VRudiApplicationsEntity r where r.id = :id")
    VRudiApplicationsEntity selectApplicationsById(Integer id);

    @Query("SELECT r from VRudiApplicationsEntity r where r.ateCode = :ateCode and r.aseCode = :aseCode and r.apnStatusCode = :apnStatusCode")
    List<VRudiApplicationsEntity> selectAllByTypeAndStatus(@Param("ateCode") String ateCode,@Param("aseCode") String aseCode,@Param("apnStatusCode") String apnStatusCode);
}
