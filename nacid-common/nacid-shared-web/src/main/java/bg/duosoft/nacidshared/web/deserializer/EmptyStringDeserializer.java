package bg.duosoft.nacidshared.web.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class EmptyStringDeserializer extends StdDeserializer<String> {

    public EmptyStringDeserializer() {
        this(null);
    }

    public EmptyStringDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public String deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);
        String value = node.textValue();

        if (!StringUtils.hasText(value)) {
            return null;
        }

        return value;
    }
}
