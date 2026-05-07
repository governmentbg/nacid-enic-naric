package bg.duosoft.nacidservicesbe.repository.rudi;

import bg.duosoft.nacidservicesbe.domain.entity.rudi.RudiApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 14.11.2022
 * Time: 11:58
 */
public interface RudiApplicationRepository extends JpaRepository<RudiApplicationEntity, Integer> {
}
