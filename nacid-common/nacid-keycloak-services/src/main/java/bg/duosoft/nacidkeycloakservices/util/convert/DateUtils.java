package bg.duosoft.nacidkeycloakservices.util.convert;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class DateUtils {

    public static final String DATE_FORMAT_DOT = "dd.MM.yyyy";

    public static LocalDate parseLocalDate(String dateToConvert) {

        if (StringUtils.hasText(dateToConvert)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_DOT);

            try {
                return LocalDate.parse(dateToConvert, formatter);
            } catch (DateTimeParseException e) {
                return null;
            }
        }

        return null;
    }

    public static String localDateToString(LocalDate date) {
        if (Objects.nonNull(date)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_DOT);
            return date.format(formatter);
        }

        return null;
    }
}
