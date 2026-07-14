package bg.duosoft.nacidservicesbe.repository.common;


import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationCorrespondenceEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationCorrespondenceFilter;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 14:53
 */
public interface ApplicationCorrespondenceRepositoryCustom {

    List<ApplicationCorrespondenceEntity> filterApplicationCorrespondence(ApplicationCorrespondenceFilter filter);

    Integer countFilteredApplicationCorrespondence(ApplicationCorrespondenceFilter filter);
}
