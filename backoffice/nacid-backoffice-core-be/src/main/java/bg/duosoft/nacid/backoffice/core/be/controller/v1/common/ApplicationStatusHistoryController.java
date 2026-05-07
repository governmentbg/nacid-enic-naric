package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNormalStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidbackofficeshareddata.service.BaseStatusService;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_STATUS_HISTORY)
@RequestMapping("/api/v1/application-status-history")
public class ApplicationStatusHistoryController extends BaseAccessController {

    private final BaseStatusService baseStatusService;
    private final ApplicationsService applicationsService;

    @Override
    public String getEditRole() {
        return SecurityRole.CORE_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }


    @GetMapping(value = "/by-application/{applicationId}")
    @ApiOperation(value = "select application status history")
    public List<ApplicationNormalStatusHistoryDTO> selectByApplicationId(@PathVariable Integer applicationId) {
        ApplicationDTO app = applicationsService.getApplicationById(applicationId);
        if (Objects.nonNull(app)) {
            return baseStatusService.selectNormalStatusHistoryByApplicationId(applicationId, app.getApplicationType().getId(), app.getApplicationSubtype().getId());
        } else {
            return null;
        }
    }

}
