package bg.duosoft.nacidkeycloakservices.util.convert;

import org.springframework.util.StringUtils;

public class BooleanUtils {

    public static Boolean convertStringToBoolean(String string) {
        if (StringUtils.hasText(string)) {
            if (string.equalsIgnoreCase("true")) {
                return true;
            } else if (string.equalsIgnoreCase("false")) {
                return false;
            }
        }
        return null;
    }
}
