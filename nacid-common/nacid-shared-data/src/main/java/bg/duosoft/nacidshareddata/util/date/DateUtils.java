package bg.duosoft.nacidshareddata.util.date;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Objects;

public class DateUtils {

    public static final String DATE_FORMAT_DOT = "dd.MM.yyyy";
    public static final String DATE_TIME_FORMAT_DOT = "dd.MM.yyyy HH:mm:ss";
    public static final String TIME_FORMAT_HOUR_MINUTE_SECOND = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert == null ? null : dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert == null ? null : (dateToConvert instanceof java.sql.Date ? ((java.sql.Date) dateToConvert).toLocalDate() : convertToLocalDateTime(dateToConvert).toLocalDate());
    }

    public static Date convertToDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static final String formatDateTime(Date date) {
        return date == null ? "" : new SimpleDateFormat(DATE_TIME_FORMAT_DOT).format(date);
    }

    public static final String formatDate(LocalDate date) {
        return formatDate(convertToDate(date));
    }
    public static final String formatDate(Date date) {
        return date == null ? "" : new SimpleDateFormat(DATE_FORMAT_DOT).format(date);
    }

    public static LocalDate convertYearToLocalDate(String year) {
        if (!StringUtils.hasText(year)) {
            return null;
        }
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();

        return LocalDate.parse(year, format);
    }

    public static LocalDate convertToLocalDate(String date) {
        if (!StringUtils.hasText(date)) {
            return null;
        }
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern(DATE_FORMAT_DOT)
                .toFormatter();

        return LocalDate.parse(date, format);
    }

    public static String convertLocalDateToYear(LocalDate localDate) {
        if (Objects.isNull(localDate)){
            return "";
        }
        return  localDate.getYear() + "";
    }

    public static String formatLocalDate(LocalDate localDate) {
        if (Objects.isNull(localDate)){
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_DOT);
        return localDate.format(formatter);
    }

    public static LocalDate parseDate(String value) {
        if(value == null) {
            return null;
        }
        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern(DATE_FORMAT_DOT));
        } catch (Exception e) {
            return null;
        }
    }
}
