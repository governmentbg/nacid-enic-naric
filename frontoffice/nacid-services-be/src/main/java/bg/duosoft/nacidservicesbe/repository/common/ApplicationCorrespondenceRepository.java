package bg.duosoft.nacidservicesbe.repository.common;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationCorrespondenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 14:03
 */
public interface ApplicationCorrespondenceRepository extends JpaRepository<ApplicationCorrespondenceEntity, Integer>, ApplicationCorrespondenceRepositoryCustom {

    @Query("SELECT c FROM ApplicationCorrespondenceEntity c WHERE c.applicationId = ?1 ORDER BY c.dateCreated DESC")
    List<ApplicationCorrespondenceEntity> getCorrespondenceForApplicationId(Integer applicationId);

}
