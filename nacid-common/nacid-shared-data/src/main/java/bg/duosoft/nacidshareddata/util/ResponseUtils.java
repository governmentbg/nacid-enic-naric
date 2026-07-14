package bg.duosoft.nacidshareddata.util;

import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ResponseUtils {

    public static <T> T notFoundCheck(T object) {
        if (Objects.isNull(object)) {
            throw new ResourceNotFoundException();
        }
        return object;
    }

    public static <T extends Collection> T notFoundCheck(T collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ResourceNotFoundException();
        }
        return collection;
    }

}
