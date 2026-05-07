package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;

public interface FoAcceptAppRequirementsService {

     void checkAcceptRequirements(CommonApplicationDTO foApplication, ApplicationSubType subType);

}
