package bg.duosoft.nacidservicesbe.repository.lib;

import bg.duosoft.nacidservicesbe.domain.entity.lib.PublicAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.08.2023
 * Time: 15:33
 */
public interface PublicAccessRepository extends JpaRepository<PublicAccessEntity, Integer> {
}
