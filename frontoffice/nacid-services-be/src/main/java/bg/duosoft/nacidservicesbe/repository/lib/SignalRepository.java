package bg.duosoft.nacidservicesbe.repository.lib;

import bg.duosoft.nacidservicesbe.domain.entity.lib.SignalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 17:18
 */
public interface SignalRepository extends JpaRepository<SignalEntity, Integer> {
}
