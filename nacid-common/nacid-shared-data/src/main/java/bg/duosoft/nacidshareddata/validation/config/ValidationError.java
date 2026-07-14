package bg.duosoft.nacidshareddata.validation.config;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ValidationError {
    private final String pointer;
    private final String message;
    private final Map<String, String> params;

    private ValidationError(String pointer, String message, Map<String, String> params) {
        this.pointer = pointer;
        this.message = message;
        this.params = params;
    }

    public static ValidationError create(String pointer, String message) {
        return new ValidationError(pointer, message, null);
    }
    public static ValidationError create(String pointer, String message,  Map<String, String> params) {
        return new ValidationError(pointer, message, params);
    }
}