package bg.duosoft.nacidservicesbe.repository.regprof;

import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofTrainingExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 17:46
 */
public interface RegprofTrainingExperienceRepository  extends JpaRepository<RegprofTrainingExperienceEntity, Integer> {

    RegprofTrainingExperienceEntity findByRegprofApplicationId(Integer regprofApplicationId);
}
