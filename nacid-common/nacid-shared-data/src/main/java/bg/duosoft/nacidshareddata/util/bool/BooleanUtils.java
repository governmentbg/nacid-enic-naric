package bg.duosoft.nacidshareddata.util.bool;

/**
 * User: ggeorgiev
 * Date: 25.05.2023
 * Time: 13:48
 */
public class BooleanUtils {
    public static boolean parseBoolean(String value, Boolean defaultValue) {
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
