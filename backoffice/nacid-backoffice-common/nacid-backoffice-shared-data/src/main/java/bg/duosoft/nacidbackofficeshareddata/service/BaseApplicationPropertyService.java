package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationProperty;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationPropertyDTO;

public interface BaseApplicationPropertyService {

    ApplicationPropertyDTO selectByType(ApplicationProperty property);

}
