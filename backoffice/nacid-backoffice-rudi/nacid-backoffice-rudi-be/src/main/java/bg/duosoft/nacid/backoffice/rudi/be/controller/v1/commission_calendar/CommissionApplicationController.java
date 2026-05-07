package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiCommissionApplicationsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarApplicationSaveDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationsService;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidshared.web.controller.CrudController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.COMMISSION_APPLICATION)
@RequestMapping("/api/v1/commission-applications")
public class CommissionApplicationController extends CrudController<Integer, CommissionApplicationDTO> {
    private final CommissionApplicationService commissionApplicationService;
    private final ApplicationsService vwApplicationsService;

    @Override
    protected CommissionApplicationService getService() {
        return commissionApplicationService;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.COMMISSION_CALENDAR_EDIT;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.COMMISSION_CALENDAR_ACCESS;
    }

    @GetMapping(value = "/ids/calendar/{calendarId}")
    @ApiOperation(value = "Select application ids by calendar id")
    public List<Integer> selectAllIdsByCalendarId(@PathVariable("calendarId") Integer calendarId) {
        return vwApplicationsService.selectAllApplicationIdsByCalendarId(calendarId);
    }


    @GetMapping(value = "/ids")
    @ApiOperation(value = "Select applications by ids")
    public List<RudiApplicationsDTO> selectApplicationsByIds(@RequestParam("ids") List<Integer> ids, @RequestParam(required = false, value = "sortColumn") String sortColumn,
                                                             @RequestParam(required = false, value = "ascOrder") Boolean ascOrder) {
        return vwApplicationsService.selectApplicationsByIdsAndSort(ids, sortColumn, ascOrder);
    }


    @GetMapping(value = "/by-calendar/ids")
    @ApiOperation(value = "Select by ids and calendar")
    public List<RudiCommissionApplicationsDTO> selectApplicationsByCalendarAndAppId(@RequestParam("ids") List<Integer> ids, @RequestParam("calendarId") Integer calendarId, @RequestParam(required = false, value = "sortColumn") String sortColumn,
                                                                                    @RequestParam(required = false, value = "ascOrder") Boolean ascOrder) {
        return commissionApplicationService.selectApplicationsByCalendarAndAppId(ids, calendarId, sortColumn, ascOrder);
    }


    @PostMapping(value = "/save")
    @ApiOperation("Insert applications")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).COMMISSION_CALENDAR_EDIT)")
    public List<Integer> saveApplications(@RequestBody CommissionCalendarApplicationSaveDTO dto) {
        commissionApplicationService.saveApplications(dto);
        return dto.getApplicationIds();
    }
}
