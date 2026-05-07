package bg.duosoft.email.nacidemailproducer.utils;

import org.springframework.data.util.Pair;

import java.sql.Timestamp;
import java.util.*;

public class TemporaryKeyUtils {

    public static String generateKey() {
        return UUID.randomUUID().toString();
    }

    public static String encodeKey(String key) {
        return Base64.getEncoder().encodeToString(key.getBytes());
    }

    public static Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public static Pair<Date, Date> calculateExpirationPeriod(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        Date createdDate = new Date(cal.getTime().getTime());
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        Date expirationDate = new Date(cal.getTime().getTime());
        return Pair.of(createdDate, expirationDate);
    }

    public static boolean isExpired(Date expirationDate) {
        if (Objects.isNull(expirationDate)) {
            throw new RuntimeException("Expiration date is empty !");
        }

        Calendar cal = Calendar.getInstance();
        if ((expirationDate.getTime() - cal.getTime().getTime()) <= 0) {
            return true;
        }
        return false;
    }

}
