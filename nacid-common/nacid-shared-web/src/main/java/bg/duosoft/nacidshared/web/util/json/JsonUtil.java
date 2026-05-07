package bg.duosoft.nacidshared.web.util.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public <C> C readJson(String json, Class<C> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> String createJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <C> List<C> readJsonList(String json, Class<C> clazz) {
        try {
            return objectMapper.readValue(json, CollectionsTypeFactory.listOf(clazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> String createJsonList(List<T> list) {
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

    public <T> T removeEmptyObjects(T object) {
        if (Objects.isNull(object)) {
            return null;
        }

        Class<T> aClass = (Class<T>) object.getClass();
        String json = createJson(object);
        if (StringUtils.hasText(json)) {
            json = json.replaceAll("\\{}", "null");
        }
        return readJson(json, aClass);
    }

}
