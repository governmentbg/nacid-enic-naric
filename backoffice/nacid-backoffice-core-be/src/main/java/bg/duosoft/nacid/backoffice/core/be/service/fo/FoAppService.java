package bg.duosoft.nacid.backoffice.core.be.service.fo;

import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;

public interface FoAppService {

    CommonApplicationDTO selectFoApplication(Integer foAppId, String appType, String subType);

}
