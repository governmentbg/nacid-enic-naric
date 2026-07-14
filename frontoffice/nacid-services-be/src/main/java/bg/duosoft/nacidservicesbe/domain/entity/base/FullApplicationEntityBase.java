package bg.duosoft.nacidservicesbe.domain.entity.base;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationEntity;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 14:14
 */
public interface FullApplicationEntityBase extends Serializable {

    Integer getId();
    void setId(Integer id);
    ApplicationEntity getApplication();
    void setApplication(ApplicationEntity application);
}
