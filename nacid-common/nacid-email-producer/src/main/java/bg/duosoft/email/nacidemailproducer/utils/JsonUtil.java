package bg.duosoft.email.nacidemailproducer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.ZoneId;
import java.util.TimeZone;

public class JsonUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
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

}
