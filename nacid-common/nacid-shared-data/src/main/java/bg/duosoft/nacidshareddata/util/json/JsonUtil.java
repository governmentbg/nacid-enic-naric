package bg.duosoft.nacidshareddata.util.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

/**
 * This class doesn't use the spring ObjectMapper where we have some global configurations
 * (for example dates serializer and deserializer) and results from using it could be different from expected.
 *
 * Use bg.duosoft.nacidshared.web.util.json.JsonUtil instead !
 **/
@Deprecated
public class JsonUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        objectMapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    public static <C> C readJson(String json, Class<C> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String createJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <C> List<C> readJsonList(String json, Class<C> clazz) {
        try {
            return objectMapper.readValue(json, CollectionsTypeFactory.listOf(clazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String createJsonList(List<T> list) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class CollectionsTypeFactory {
        static JavaType listOf(Class clazz) {
            return TypeFactory.defaultInstance().constructCollectionType(List.class, clazz);
        }

    }

    public static <T> T removeEmptyObjects(T object) {
        if (Objects.isNull(object)) {
            return null;
        }

        Class<T> aClass = (Class<T>) object.getClass();
        String json = JsonUtil.createJson(object);
        if (StringUtils.hasText(json)) {
            json = json.replaceAll("\\{}", "null");
        }
        return JsonUtil.readJson(json, aClass);
    }

}
