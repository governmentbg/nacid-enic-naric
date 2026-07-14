package bg.duosoft.nacidservicesbe.repository.rudi;

import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiTrainingCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.11.2022
 * Time: 11:37
 */
public interface RudiTrainingCourseRepository extends JpaRepository<RudiTrainingCourseEntity, Integer> {

    RudiTrainingCourseEntity findByRudiApplicationId(Integer rudiApplicationId);
}
