package bg.duosoft.nacid.backoffice.core.be.repository.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationBaseDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.inquiry.PublicationInfoDTO;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.ApplicationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 25.08.2022
 * Time: 14:10
 */
public interface ApplicationRepository extends BaseRepository<ApplicationEntity, Integer> {
    @Query("SELECT distinct a.userCreated from ApplicationEntity a where a.applicationType.id = :applicationType")
    List<String> getAllUserCreatedByApplicationType(@Param("applicationType") String applicationType);

    @Query(value = "SELECT distinct aru.responsible_user from common.application a join common.application_responsible_users aru on a.id = aru.apn_id where a.ate_code=?1", nativeQuery = true)
    List<String> getAllResponsibleUsersByApplicationType(@Param("applicationType") String applicationType);

    @Query(value = "SELECT a.ate_code, a.ase_code FROM common.application a where a.id = :id", nativeQuery = true)
    Object[] selectAppTypeAndSubtypeById(@Param("id") Integer id);

    @Query(value = "SELECT a from ApplicationEntity a inner join a.documentReceiveMethods m where m.documentRecipientAddress.id = :addressId order by a.dateCreated desc ")
    List<ApplicationEntity> getApplicationsByDocRecipientAddressId(@Param("addressId") Integer addressId);

    @Query(value = "SELECT a.status.pk.id from ApplicationEntity a where a.id = :applicationId ")
    String selectStatusCodeById(@Param("applicationId") Integer applicationId);

    @Query(value = "SELECT count (a) from ApplicationEntity a inner join a.documentReceiveMethods m where m.documentRecipientAddress.id = :addressId")
    Integer getApplicationsCountByDocRecipientAddressId(@Param("addressId") Integer addressId);

    @Query(value = "SELECT a from ApplicationEntity a where a.contactAddress.id = :addressId order by a.dateCreated desc ")
    List<ApplicationEntity> getApplicationsByContactAddressId(@Param("addressId") Integer addressId);

    @Query(value = "SELECT count (a) from ApplicationEntity a where a.contactAddress.id = :addressId")
    Integer getApplicationsCountByContactAddressId(@Param("addressId") Integer addressId);

    @Query(value = "SELECT DISTINCT p.application_id as applicationId, " +
            "                p.entry_num as entryNum, " +
            "                p.date_created as dateCreated, " +
            "                rs.name as statusName, " +
            "                p.ate_code as appType, " +
            "                p.ase_code as appSubType, " +
            "                ds.name as docflowStatusName " +
            "FROM common.vw_applications_by_person p " +
            "JOIN nomenclatures.reference_data rs on p.status_code = rs.code AND rs.domain = 'APPLICATION_STATUS' " +
            "JOIN nomenclatures.reference_data ds on p.docflow_status_code = ds.code AND ds.domain = 'DOCFLOW_STATUS' " +
            "where p.person_id = :personId order by p.date_created desc", nativeQuery = true)
    List<AppPersonProjection> getApplicationsByPersonId(@Param("personId") Integer personId);

    @Query(value = "SELECT count(DISTINCT p.application_id) " +
            "FROM common.vw_applications_by_person p " +
            "where p.person_id = :personId", nativeQuery = true)
    Integer getApplicationsCountByPersonId(@Param("personId") Integer personId);

    @Query(value = "SELECT a.date_created FROM common.application a where a.id = :id", nativeQuery = true)
    LocalDateTime getDateCreated(@Param("id") Integer id);

    @Query("SELECT r.efilingId from ApplicationEntity r where r.id = :id")
    Integer selectEfilingIdByApplicationId(@Param("id") Integer id);

    @Query("SELECT r.id from ApplicationEntity r where r.efilingId = :efilingId")
    Integer selectApplicationIdByEfilingId(@Param("efilingId") Integer efilingId);

    @Query("SELECT a.status.pk.id from ApplicationEntity a where a.entryNumber = :entryNumber and a.entryDate = :entryDate")
    Optional<String> selectStatusCodeByEntryDetails(@Param("entryNumber") String entryNumber, @Param("entryDate") LocalDate entryDate);

    @Query("SELECT a.id from ApplicationEntity a where a.entryNumber = :entryNumber and a.entryDate = :entryDate")
    Optional<Integer> selectApnIdByEntryDetails(@Param("entryNumber") String entryNumber, @Param("entryDate") LocalDate entryDate);

    @Query("SELECT NEW bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationBaseDataDTO(a.id, a.applicationType.id, a.applicationSubtype.id) FROM ApplicationEntity a " +
            " where a.entryNumber = :entryNumber and a.entryDate = :entryDate ")
    ApplicationBaseDataDTO getApplicationBaseData(@Param("entryNumber") String entryNumber, @Param("entryDate") LocalDate entryDate);

    @Query("UPDATE ApplicationEntity e set e.paidFlag = :paidFlag where e.entryNumber = :entryNumber and e.entryDate = :entryDate")
    @Modifying
    void updateApplicationPaidFlag(@Param("entryNumber") String entryNumber, @Param("entryDate") LocalDate entryDate, @Param("paidFlag") Integer paidFlag);

    interface AppPersonProjection {
        Integer getApplicationId();

        String getEntryNum();

        LocalDateTime getDateCreated();

        String getStatusName();

        String getDocflowStatusName();

        String getAppType();

        String getAppSubType();
    }

}
