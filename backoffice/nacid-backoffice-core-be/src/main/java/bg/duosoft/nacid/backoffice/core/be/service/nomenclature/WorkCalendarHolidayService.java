package bg.duosoft.nacid.backoffice.core.be.service.nomenclature;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.WorkCalendarHolidayDTO;

import java.time.LocalDate;
import java.util.List;

public interface WorkCalendarHolidayService {

    void delete(LocalDate id);

    WorkCalendarHolidayDTO save(WorkCalendarHolidayDTO workCalendarHoliday);

    List<WorkCalendarHolidayDTO> selectHolidaysForAnYear(Integer year);

    List<Integer> selectAllYears();

    Integer initializeYear(Integer year);

    LocalDate calculateWorkingDaysPeriod(LocalDate startDate, int workingDays);

}
