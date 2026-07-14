package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidfrontofficedto.Page;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationListFilterDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationListRecordDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidservicesclient.client.ServicesBoApiClient;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.FO_APPLICATIONS_LIST)
@RequestMapping("/api/v1/fo-applications-list")
public class FoApplicationsListController extends BaseAccessController {
    private final ServicesBoApiClient servicesBoApiClient;
    private final ApplicationsService applicationsService;

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.FO_APPS_ACCEPTANCE_ACCESS;
    }


    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter records")
    public Page<ApplicationListRecordDTO> searchData(ApplicationListFilterDTO filter) {
        if (!StringUtils.hasText(filter.getFoStatusSelectValue())){
            filter.setFoStatusesExclude(Arrays.asList(FoApplicationStatus.ACCEPTANCE_DENIED,FoApplicationStatus.ACCEPTED));
        }
        Page<ApplicationListRecordDTO> applications = servicesBoApiClient.filterApplications(filter);
        for (ApplicationListRecordDTO apn : applications.getContent()) {
            Integer apnId = applicationsService.getApplicationIdByEntryDetails(apn.getEntryNumber(), apn.getEntryDate());
            if (Objects.nonNull(apnId)) {
                apn.setBoApnId(apnId);
            }
        }
        return applications;
    }
}
