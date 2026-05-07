package bg.duosoft.nacidservicesbe.repository.common;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationIdIndexIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.11.2022
 * Time: 18:03
 */
public interface ApplicationAttachedDocsRepository extends JpaRepository<ApplicationAttachedDocEntity, ApplicationIdIndexIdEntity> {

    void removeAllById_ApplicationId(Integer applicationId);
}
