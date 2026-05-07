package bg.duosoft.nacidservicesbe.repository.common;

import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationFilter;
import bg.duosoft.nacidservicesbe.domain.entity.common.VwApplicationEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.12.2022
 * Time: 16:01
 */
public interface VwApplicationRepositoryCustom {

    List<VwApplicationEntity> filterApplications(ApplicationFilter filter);

    Integer countFilteredApplications(ApplicationFilter filter);
}
