package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionCalendarDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.commission_calendar.CalendarMainDataSectionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar.CalendarMainDataSectionMapper;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionCalendarService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_ACCESS;
import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_EDIT;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.COMMISSION_CALENDAR_MAIN_DATA)
@RequestMapping("/api/v1/commission-calendars/main-data")
public class CommissionCalendarMainDataController extends BaseAccessController {
    private final CalendarMainDataSectionMapper calendarMainDataSectionMapper;
    private final CommissionCalendarService commissionCalendarService;


    @Override
    public String getEditRole() {
        return RUDI_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return RUDI_APPLICATION_ACCESS;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Select main data")
    public CalendarMainDataSectionDTO selectById(@PathVariable Integer id) {
        CommissionCalendarDTO commissionCalendarDTO = commissionCalendarService.selectById(id);
        return calendarMainDataSectionMapper.toMainDataSection(commissionCalendarDTO);
    }

    @PostMapping
    @ApiOperation("Insert calendar")
    public CalendarMainDataSectionDTO create(@RequestBody CalendarMainDataSectionDTO dto) {
        CommissionCalendarDTO commissionCalendarWithMainData = new CommissionCalendarDTO();
        calendarMainDataSectionMapper.overrideCalendarMainData(dto, commissionCalendarWithMainData);
        commissionCalendarWithMainData.setSessionNum(commissionCalendarService.getMaxSessionNum());
        CommissionCalendarDTO createdCommissionCalendar = commissionCalendarService.create(commissionCalendarWithMainData);
        return calendarMainDataSectionMapper.toMainDataSection(createdCommissionCalendar);
    }




    @PutMapping
    @ApiOperation("Update calendar")
    public CalendarMainDataSectionDTO update(@RequestBody CalendarMainDataSectionDTO dto) {
        if (Objects.isNull(dto.getId())) {
            throw new ResourceNotFoundException();
        }
        CommissionCalendarDTO existedCalendar = commissionCalendarService.selectById(dto.getId());
        calendarMainDataSectionMapper.overrideCalendarMainData(dto, existedCalendar);
        CommissionCalendarDTO updatedCalendar = commissionCalendarService.update(existedCalendar);
        return calendarMainDataSectionMapper.toMainDataSection(updatedCalendar);
    }
}
