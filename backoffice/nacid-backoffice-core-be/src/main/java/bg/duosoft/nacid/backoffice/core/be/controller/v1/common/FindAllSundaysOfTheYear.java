package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class FindAllSundaysOfTheYear {
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