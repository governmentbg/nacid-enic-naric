package bg.duosoft.nacidservicesbe.repository.common;

import bg.duosoft.nacidservicesbe.domain.entity.common.MultipleApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 30.05.2023
 * Time: 14:09
 */
public interface MultipleApplicationRepository extends JpaRepository<MultipleApplicationEntity, Integer> {

    @Query("SELECT distinct m FROM MultipleApplicationEntity m join m.applications a WHERE a.id = ?1")
    List<MultipleApplicationEntity> findMultipleApplicationsForApplicationId(Integer singleApplicationId);
}
