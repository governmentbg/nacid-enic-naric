package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.commission_calendar;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CalendarProcessDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionCalendarDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.VCommissionCalendarDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.calendar.CalendarProtocolsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarGlobalReportDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CommissionCalendarFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionCalendarService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidbackofficeshareddata.service.AbdocsAutoFileTransferService;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidshared.web.controller.CrudController;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.COMMISSION_CALENDAR)
@RequestMapping("/api/v1/commission-calendars")
public class CommissionCalendarController extends CrudController<Integer, CommissionCalendarDTO> {

    private final CommissionCalendarService service;
    private final AbdocsAutoFileTransferService abdocsAutoFileTransferService;
    private final CommissionApplicationService commissionApplicationService;

    @Override
    protected CommissionCalendarService getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.COMMISSION_CALENDAR_EDIT;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.COMMISSION_CALENDAR_ACCESS;
    }


    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter records")
    public Page<VCommissionCalendarDTO> searchData(CommissionCalendarFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<VCommissionCalendarDTO> results = service.searchRecords(filter);
        return new Page<>(service.getRecordsCount(filter), results, filter.getPageSize());
    }

    @GetMapping(value = "/process-data")
    @ApiOperation(value = "get process data")
    public CalendarProcessDataDTO getProcessData(@RequestParam("calendarId") Integer calendarId, @RequestParam("applicationId") Integer applicationId) {
        return service.getProcessData(calendarId, applicationId);
    }

    @GetMapping(value = "/secretary")
    @ApiOperation(value = "get secretary")
    public String getSecretary(@RequestParam("calendarId") Integer calendarId) {
        return service.getSecretary(calendarId);
    }

    @GetMapping(value = "/protocol")
    @ApiOperation(value = "get protocol")
    public AttachmentDTO getProtocol(@RequestParam("calendarId") Integer calendarId) {
        return service.getCalendarProtocol(calendarId);
    }

    @GetMapping(value = "/protocols")
    @ApiOperation(value = "get protocols")
    public CalendarProtocolsDTO getProtocols(@RequestParam("calendarId") Integer calendarId) {
        return service.getCalendarProtocols(calendarId);
    }

    @PostMapping(value = "/process-data/save")
    @ApiOperation("Save process data")
    public void saveProcessData(@RequestBody CalendarProcessDataDTO processData) {
        service.saveProcessData(processData);
    }


    @GetMapping(value = "/{id}/exists")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Check if rudi application exists")
    public void checkIfExists(@PathVariable Integer id) {
        boolean exists = service.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException();
        }
    }

    @GetMapping(value = "/{id}/full-number")
    public String getCalendarFullNumber(@PathVariable Integer id) {
        return service.getFullNumber(id);
    }


    @PutMapping("/protocol/{calendarId}")
    @ApiOperation("Update protocol")
    public AttachmentDTO updateProtocol(@PathVariable Integer calendarId, @RequestBody AttachmentDTO protocol) {
        return service.updateProtocol(calendarId, protocol);
    }

    @PutMapping("/protocols/{calendarId}")
    @ApiOperation("Update protocols")
    public CalendarProtocolsDTO updateProtocols(@PathVariable Integer calendarId, @RequestBody CalendarProtocolsDTO protocols) {
        return service.updateProtocols(calendarId, protocols);
    }

    @PostMapping("/transfer-missing-abdocs-documents/{calendarId}")
    public void transferMissingAbdocsDocuments(@PathVariable Integer calendarId) {
        List<CommissionApplicationDTO> commissionApplications = commissionApplicationService.selectByCalendarId(calendarId);
        if (!CollectionUtils.isEmpty(commissionApplications)) {
            for (CommissionApplicationDTO commissionApplication : commissionApplications) {
                if (Objects.nonNull(commissionApplication.getAttachedDoc()) && Objects.isNull(commissionApplication.getAttachedDoc().getDocflowId())) {
                    abdocsAutoFileTransferService.transferApplicationFiles(commissionApplication.getApplicationId());
                }
            }
        }
    }

}
