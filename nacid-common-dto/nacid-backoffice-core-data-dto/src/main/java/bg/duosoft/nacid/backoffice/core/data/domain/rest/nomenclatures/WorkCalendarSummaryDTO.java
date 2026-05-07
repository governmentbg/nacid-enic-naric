package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkCalendarSummaryDTO {

    private Integer year;
    private Integer workingDaysCount;
    private Integer holidayDaysCount;
    private List<WorkCalendarHolidayDTO> holidaysWithDescription;

}
