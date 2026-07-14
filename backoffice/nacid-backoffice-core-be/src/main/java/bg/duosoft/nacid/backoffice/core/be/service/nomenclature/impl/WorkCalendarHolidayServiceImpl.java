package bg.duosoft.nacid.backoffice.core.be.service.nomenclature.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.WorkCalendarHolidayRepository;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.WorkCalendarHolidayService;
import bg.duosoft.nacid.backoffice.core.be.util.WorkCalendarUtis;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.WorkCalendarHolidayEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.WorkCalendarHolidayDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.WorkCalendarHolidayMapper;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.util.date.DateUtils;
import bg.duosoft.nacidshareddata.util.regex.RegexUtils;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WorkCalendarHolidayServiceImpl implements WorkCalendarHolidayService {

    private final WorkCalendarHolidayRepository repository;
    private final WorkCalendarHolidayMapper mapper;

    @Override
    @CacheEvict(allEntries = true, cacheNames = {"WorkCalendarHolidayService"})
    public WorkCalendarHolidayDTO save(WorkCalendarHolidayDTO workCalendarHoliday) {
        WorkCalendarHolidayEntity entity = mapper.toEntity(workCalendarHoliday);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @CacheEvict(allEntries = true, cacheNames = {"WorkCalendarHolidayService"})
    public void delete(LocalDate id) {
        if (Objects.isNull(id)) {
            return;
        }

        repository.deleteById(id);
    }

    @Cacheable(value = "WorkCalendarHolidayService", key = "'work-calendar-holiday-' +  #year")
    public List<WorkCalendarHolidayDTO> selectHolidaysForAnYear(Integer year) {
        if (Objects.isNull(year)) {
            return null;
        }

        boolean isValidYear = Pattern.compile(RegexUtils.YEAR_VALIDATION_REGEX).matcher(String.valueOf(year)).matches();
        if (!isValidYear) {
            throw new RuntimeException("Invalid year for work calendar ! Year: " + year);
        }

        LocalDate startDate = DateUtils.convertYearToLocalDate(String.valueOf(year));
        LocalDate endDate = startDate.with(lastDayOfYear());
        List<WorkCalendarHolidayEntity> entities = repository.selectByDateRange(startDate, endDate);
        return mapper.toDtoList(entities);
    }

    @Override
    @Cacheable(value = "WorkCalendarHolidayService", key = "'work-calendar-holiday-all-years'")
    public List<Integer> selectAllYears() {
        List<Integer> years = repository.selectAllYears();
        if (CollectionUtils.isEmpty(years)) {
            return null;
        }
        Collections.sort(years, Collections.reverseOrder());
        return years;
    }

    @Override
    @CacheEvict(allEntries = true, cacheNames = {"WorkCalendarHolidayService"})
    public Integer initializeYear(Integer year) {
        boolean isValidYear = Pattern.compile(RegexUtils.YEAR_VALIDATION_REGEX).matcher(String.valueOf(year)).matches();
        if (!isValidYear) {
            throw new RuntimeException("Invalid year! Year: " + year);
        }

        List<Integer> existingYears = selectAllYears();
        if (!CollectionUtils.isEmpty(existingYears)) {
            boolean isAlreadyAdded = existingYears.stream().anyMatch(y -> y.equals(year));
            if (isAlreadyAdded) {
                throw new RuntimeException("Selected year has been already added to the database !");
            }
        }

        List<WorkCalendarHolidayDTO> initialHolidays = WorkCalendarUtis.selectAllSaturdaysAndSundays(year).stream().map(h -> {
            WorkCalendarHolidayDTO holidayDTO = new WorkCalendarHolidayDTO();
            holidayDTO.setId(h);
            holidayDTO.setUserLastUpdate(SecurityUtils.getUsername());
            holidayDTO.setDateLastUpdate(LocalDateTime.now());
            return holidayDTO;
        }).toList();

        for (WorkCalendarHolidayDTO holidayDTO : initialHolidays) {
            save(holidayDTO);
        }

        return year;
    }

    @Override
    public LocalDate calculateWorkingDaysPeriod(LocalDate startDate, int workingDays) {
        if (Objects.isNull(startDate)) {
            return null;
        }
        List<LocalDate> holidays = getHolidaysForRange(startDate, workingDays);

        LocalDate endDate = startDate;
        for (int i = 0; i < workingDays; i++) {
            endDate = addWorkingDays(endDate, holidays);
        }
        return endDate;
    }

    private LocalDate addWorkingDays(LocalDate startDate, List<LocalDate> holidays) {
        LocalDate nextDay = startDate.plusDays(1);
        if (CollectionUtils.isEmpty(holidays)) {
            return nextDay;
        }

        boolean isHoliday = holidays.stream().anyMatch(h -> h.isEqual(nextDay));
        if (isHoliday) {
            return addWorkingDays(nextDay, holidays);
        } else {
            return nextDay;
        }
    }

    private List<LocalDate> getHolidaysForRange(LocalDate startDate, int workingDays) {
        List<WorkCalendarHolidayDTO> holidays = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();

        List<Integer> allCalendarYears = selectAllYears();
        int currentYear = startDate.getYear();
        if (!allCalendarYears.contains(currentYear)) {
            errors.add(ValidationError.create("workCalendar", "validation.not.initialized.current.work.calendar"));
            throw new ValidationErrorException(errors);

        }

        List<WorkCalendarHolidayDTO> holidaysCurrentYear = selectHolidaysForAnYear(currentYear);
        if (!CollectionUtils.isEmpty(holidaysCurrentYear)) {
            holidays.addAll(holidaysCurrentYear);
        }

        int plusYears = (workingDays / 365) + 1;
        for (int i = 1; i < plusYears + 1; i++) {
            int nextYear = startDate.getYear() + i;
            if (!allCalendarYears.contains(nextYear)) {
                errors.add(ValidationError.create("workCalendar", "validation.not.initialized.next.work.calendar"));
                throw new ValidationErrorException(errors);
            }
            List<WorkCalendarHolidayDTO> holidaysNextYear = selectHolidaysForAnYear(nextYear);
            if (!CollectionUtils.isEmpty(holidaysNextYear)) {
                holidays.addAll(holidaysNextYear);
            }
        }

        if (CollectionUtils.isEmpty(holidays)) {
            return null;
        }

        return holidays.stream().map(WorkCalendarHolidayDTO::getId).toList();
    }
}
