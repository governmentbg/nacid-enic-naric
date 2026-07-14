package bg.duosoft.nacidkeycloakservices.util.attribute;

import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.07.2022
 * Time: 13:33
 */
public class AttributesUtil {

    public static void setAttribute(Map<String, List<String>> attributes, String attributeName, String value) {
        if (StringUtils.hasText(value)) {
            attributes.put(attributeName, Collections.singletonList(value));
        } else {
            attributes.remove(attributeName);
        }
    }
}
