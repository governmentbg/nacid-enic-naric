package bg.duosoft.nacidshareddata.validation.config;


import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.util.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
public class BadRequestValidator {

    public static <T extends Validator, O> void validateRequest(T validator, O object, Object... args) {
        List<ValidationError> errors = validator.validate(object, args);
        if (!CollectionUtils.isEmpty(errors)) {
            log.error("[BAD REQUEST] {} \n{}", errors, JsonUtil.createJson(object));
            throw new ValidationErrorException(errors);
        }
    }

}