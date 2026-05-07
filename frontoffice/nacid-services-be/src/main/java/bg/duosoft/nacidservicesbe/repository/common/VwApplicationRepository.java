package bg.duosoft.nacidservicesbe.repository.common;

import bg.duosoft.nacidservicesbe.domain.entity.common.VwApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 12.05.2023
 * Time: 12:47
 */
public interface VwApplicationRepository extends JpaRepository<VwApplicationEntity, Integer>, VwApplicationRepositoryCustom {

    @Query("SELECT a.foStatusCode FROM VwApplicationEntity a WHERE a.id=?1")
    String getFoStatusCode(Integer id);

    @Query("SELECT a.applicationSubtypeCode FROM VwApplicationEntity a WHERE a.entryNumber=?1 and a.entryDate =?2 and a.accessCode=?3 and a.foStatusCode in ?4")
    String getApplicationSubtypeCode(String entryNumber, LocalDate entryDate, String accessCode, List<String> allowedStatusCodes);

    @Query("SELECT DISTINCT a.lastStatusName FROM VwApplicationEntity a WHERE a.userCreated = ?1 ORDER BY a.lastStatusName asc")
    List<String> getAllLastStatusesByUser(String user);
}
