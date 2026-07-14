package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshared.web.util.json.JsonUtil;
import bg.duosoft.nacidshareddata.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_ACCESS;
import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_EDIT;

@Slf4j
public abstract class RudiAppDataBaseController extends BaseAccessController {

    @Autowired
    protected RudiApplicationService rudiApplicationService;

    @Autowired
    protected JsonUtil jsonUtil;

    @Override
    public String getEditRole() {
        return RUDI_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return RUDI_APPLICATION_ACCESS;
    }

    protected RudiApplicationDTO selectOriginalApplication(Integer id) {
        return ResponseUtils.notFoundCheck(rudiApplicationService.selectById(id));
    }
}
