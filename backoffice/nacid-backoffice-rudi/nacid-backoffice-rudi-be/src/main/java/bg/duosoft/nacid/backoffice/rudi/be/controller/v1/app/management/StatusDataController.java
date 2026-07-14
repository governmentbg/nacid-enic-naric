package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InitialConstraintDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.RudiStatusDataBaseDTO;
import bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app.management.base.RudiAppDataBaseController;
import bg.duosoft.nacid.backoffice.rudi.be.domain.factory.RudiStatusDataObjectFactory;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.provider.RudiStatusDataMapperProvider;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationStatusService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.STATUS_EDIT;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_STATUS)
@RequestMapping("/api/v1/applications/data/status")
public class StatusDataController extends RudiAppDataBaseController {
    private final RudiStatusDataMapperProvider mapperProvider;
    private final RudiStatusDataObjectFactory objectFactory;
    private final ApplicationStatusService applicationStatusService;


    @GetMapping(value = "/initial-data-examination/{id}")
    @ApiOperation(value = "Application initial data examination")
    public InitialConstraintDTO statusInitialDataExamination(@PathVariable Integer id) {
        RudiApplicationDTO application = rudiApplicationService.selectById(id);
        if (Objects.isNull(application)) {
            throw new ResourceNotFoundException();
        }
        return applicationStatusService.examineStatusInitialData(application);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select application status data")
    public RudiStatusDataBaseDTO selectById(@PathVariable Integer id) {
        RudiApplicationDTO app = selectOriginalApplication(id);
        return mapperProvider.getMapper(app).toStatusDataSection(app);
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Update status data")
    public RudiStatusDataBaseDTO updateStatusData(@PathVariable Integer id,  @RequestBody String requestData) {
        RudiApplicationDTO app = selectOriginalApplication(id);
        RudiStatusDataBaseDTO statusDataDto = objectFactory.createObject(app, requestData);
        statusDataDto.setApplicationId(id);
        return applicationStatusService.insertStatus(statusDataDto);
    }

    @Override
    public String getEditRole() {
        return STATUS_EDIT;
    }

}
