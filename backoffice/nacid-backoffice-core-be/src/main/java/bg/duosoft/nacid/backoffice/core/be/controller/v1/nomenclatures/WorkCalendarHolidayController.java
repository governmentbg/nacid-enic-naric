package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.WorkCalendarHolidayService;
import bg.duosoft.nacid.backoffice.core.be.util.WorkCalendarUtis;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.WorkCalendarHolidayDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.WorkCalendarSummaryDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_WORK_CALENDAR)
@RequestMapping("/api/v1/work-calendar/holidays")
public class WorkCalendarHolidayController extends BaseAccessController {

    private final WorkCalendarHolidayService service;

    @GetMapping(value = "/{year}")
    @ApiOperation(value = "Select holidays for an year")
    public List<WorkCalendarHolidayDTO> selectHolidaysByYear(@PathVariable Integer year) {
        return service.selectHolidaysForAnYear(year);
    }

    @GetMapping(value = "/years")
    @ApiOperation(value = "Select all unique years from work calendar")
    public List<Integer> selectYears() {
        return service.selectAllYears();
    }

    @PostMapping("/years/{year}")
    @ApiOperation("Initialize year")
    public Integer initYear(@PathVariable Integer year) {
        return service.initializeYear(year);
    }

    @GetMapping(value = "/{year}/summary")
    @ApiOperation(value = "Get work calendar summary for year")
    public WorkCalendarSummaryDTO getSummaryForYear(@PathVariable Integer year) {
        List<WorkCalendarHolidayDTO> holidays = service.selectHolidaysForAnYear(year);
        Pair<Integer, Integer> yearDaysCountPair = WorkCalendarUtis.getYearDaysCount(year, holidays);

        WorkCalendarSummaryDTO summary = new WorkCalendarSummaryDTO();
        summary.setYear(year);
        summary.setWorkingDaysCount(yearDaysCountPair.getFirst());
        summary.setHolidayDaysCount(yearDaysCountPair.getSecond());
        if (!CollectionUtils.isEmpty(holidays)) {
            summary.setHolidaysWithDescription(holidays.stream().filter(h -> StringUtils.hasText(h.getDescription())).toList());
        }

        return summary;
    }

    @PostMapping
    @ApiOperation("Add holiday")
    public WorkCalendarHolidayDTO addHoliday(@RequestBody WorkCalendarHolidayDTO holidayDTO) {
        holidayDTO.setDateLastUpdate(LocalDateTime.now());
        holidayDTO.setUserLastUpdate(SecurityUtils.getUsername());
        return service.save(holidayDTO);
    }

    @DeleteMapping("/{dateString}")
    @ApiOperation("Remove holiday")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeHoliday(@PathVariable String dateString) {
        LocalDate localDate = DateUtils.convertToLocalDate(dateString);
        service.delete(localDate);
    }

    @Override
    public String getEditRole() {
        return SecurityRole.WORK_CALENDAR_EDIT;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.WORK_CALENDAR_ACCESS;
    }

}
