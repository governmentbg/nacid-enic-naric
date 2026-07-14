package bg.duosoft.email.nacidemailproducer.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DOT = "dd.MM.yyyy";
    public static final String DATE_TIME_FORMAT_DOT = "dd/MM/yyyy HH:mm:ss";
    public static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat(DATE_TIME_FORMAT_DOT);

    public static String formatDateTime(Date date) {
        return date == null ? "" : DATE_TIME_FORMATTER.format(date);
    }

    public static Date toTheEndOfTheDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
}
