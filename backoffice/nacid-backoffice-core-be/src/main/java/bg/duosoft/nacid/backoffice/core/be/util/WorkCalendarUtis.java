package bg.duosoft.nacid.backoffice.core.be.util;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.WorkCalendarHolidayDTO;
import org.springframework.data.util.Pair;
import org.springframework.util.CollectionUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class WorkCalendarUtis {


    public static Pair<Integer, Integer> getYearDaysCount(Integer year, List<WorkCalendarHolidayDTO> holidays) {
        int yearDaysCount = Year.of(year).length();

        int holidaysCount = 0;
        if (!CollectionUtils.isEmpty(holidays)) {
            holidaysCount = holidays.size();
        }

        int workingDays = yearDaysCount - holidaysCount;
        return Pair.of(workingDays, holidaysCount);
    }

    public static List<LocalDate> selectAllSaturdaysAndSundays(Integer year) {
        List<LocalDate> result = new ArrayList<>();

        List<LocalDate> saturdays = selectDaysInYear(year, DayOfWeek.SATURDAY);
        result.addAll(saturdays);

        List<LocalDate> sundays = selectDaysInYear(year, DayOfWeek.SUNDAY);
        result.addAll(sundays);

        return result;
    }

    public static List<LocalDate> selectDaysInYear(Integer year, DayOfWeek dayOfWeek) {
        List<LocalDate> result = new ArrayList<>();
        LocalDate now = LocalDate.of(year, Month.JANUARY, 1);
        // Find the first Sunday of the year
        LocalDate sunday = now.with(firstInMonth(dayOfWeek));
        do {
            result.add(sunday);
            // Loop to get every Sunday by adding Period.ofDays(7) to the current Sunday.
            sunday = sunday.plus(Period.ofDays(7));
        } while (sunday.getYear() == year);

        return result;
    }

    public static void main(String[] args) {
        // Create a LocalDate object that represent the first day of the year.

        int count = 0;
        for (int i = 1965; i < 2023; i++) {
            int year = i;
            LocalDate now = LocalDate.of(year, Month.JANUARY, 1);
            // Find the first Sunday of the year
            LocalDate sunday = now.with(firstInMonth(DayOfWeek.SUNDAY));

            do {
                // Loop to get every Sunday by adding Period.ofDays(7) to the current Sunday.
                System.out.println(sunday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
                sunday = sunday.plus(Period.ofDays(7));
                count++;
            } while (sunday.getYear() == year);
        }

        System.out.println(count);

    }
}
