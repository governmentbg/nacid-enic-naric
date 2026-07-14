package bg.duosoft.nacidservicesbe.repository.common;

import bg.duosoft.nacidservicesbe.domain.entity.common.AppStatusHistoryEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.11.2022
 * Time: 11:55
 */
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {

    boolean existsByIdAndUserCreated(Integer id, String userCreated);

    @Query("SELECT a.applicationSubtype.id FROM ApplicationEntity a WHERE a.id=?1")
    String getApplicationSubtypeCode(Integer id);

    @Query("SELECT a.id FROM ApplicationEntity a WHERE a.entryNumber=?1 and a.entryDate =?2 and a.accessCode =?3")
    Integer getApplicationIdForDossierNumberAccessCode(String entryNumber, LocalDate entryDate, String accessCode);

    @Query("SELECT a.tempNumber FROM ApplicationEntity a WHERE a.id=?1")
    String getApplicationTempNumber(Integer id);

    @Query("SELECT a.userCreated FROM ApplicationEntity a WHERE a.id=?1")
    String getApplicationUserCreated(Integer id);

    @Query("SELECT count(h) FROM ApplicationEntity a JOIN a.statusHistory h WHERE a.id=?1 and h.foStatus is not null and h.foStatus.pk.id=?2")
    Long countFoStatusesForCode(Integer id, String statusCode);

    @Query("SELECT a.dateCreated FROM ApplicationEntity a WHERE a.id=?1")
    LocalDate getApplicationDateCreated(Integer id);

    @Modifying
    @Query("UPDATE ApplicationEntity a SET a.paidFlag = ?1 WHERE a.tempNumber=?2")
    void updatePaidFlag(Integer paidFlag, String tempNumber);

    @Query("SELECT a.statusHistory FROM ApplicationEntity a WHERE a.id=?1")
    List<AppStatusHistoryEntity> getApplicationStatusHistory(Integer id);
}
