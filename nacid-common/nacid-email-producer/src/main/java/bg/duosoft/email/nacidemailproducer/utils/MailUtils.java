package bg.duosoft.email.nacidemailproducer.utils;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MailUtils {

    public static String replaceTemplatePlaceholders(Map<String, String> parameters, String initialText) {
        String result = initialText;

        if (Objects.nonNull(parameters)) {
            Set<String> keySet = parameters.keySet();
            if (!CollectionUtils.isEmpty(keySet)) {
                for (String key : keySet) {
                    String replaceText = parameters.get(key);
                    if (Objects.isNull(replaceText)) {
                        replaceText = "";
                    }
                    result = result.replace(key, replaceText);
                }
            }
        }

        return result;
    }

    public static Map<String, String> normalizeTemplateParams(Map<String, String> templateParams) {
        Map<String, String> params = new HashMap<>();

        Set<String> keys = templateParams.keySet();
        if (!CollectionUtils.isEmpty(keys)) {
            for (String key : keys) {
                String normalizedKey = key.replace("{", "").replace("}", "").replace("$", "");
                params.put("{" + normalizedKey + "}", templateParams.get(key));
            }
        }
        return params;
    }

}
