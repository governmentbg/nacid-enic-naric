package bg.duosoft.nacidkeycloakservices.util.attribute;

import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class Extractor {

    public static <T> T firstOrNull(Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return null;
        }
        if (collection.size() == 1) {
            return collection.stream().findFirst().orElse(null);
        }
        return null;
    }
}
