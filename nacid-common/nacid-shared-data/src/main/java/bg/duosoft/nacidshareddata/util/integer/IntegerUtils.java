package bg.duosoft.nacidshareddata.util.integer;

public class IntegerUtils {
    public static Integer parseInteger(String value, Integer defaultValue) {
        try {
            return value == null ? defaultValue : Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
