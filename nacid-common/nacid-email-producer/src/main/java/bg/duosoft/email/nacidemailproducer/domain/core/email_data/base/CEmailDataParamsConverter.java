package bg.duosoft.email.nacidemailproducer.domain.core.email_data.base;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class CEmailDataParamsConverter {

    public Map<String, String> createParamsMap() {
        return new ObjectMapper().convertValue(this, Map.class);
    }
}
