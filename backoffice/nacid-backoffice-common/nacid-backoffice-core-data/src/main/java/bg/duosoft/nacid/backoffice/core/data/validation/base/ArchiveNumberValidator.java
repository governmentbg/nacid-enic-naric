package bg.duosoft.nacid.backoffice.core.data.validation.base;

import bg.duosoft.nacidshareddata.validation.config.DefaultValidation;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.util.StringUtils;

import java.util.List;

public interface ArchiveNumberValidator extends DefaultValidation {
    
    default void validateArchiveNumber(List<ValidationError> errors, String archiveNumber, String pointer) {
        if (StringUtils.hasText(archiveNumber)) {
            rejectIfTrue(errors, archiveNumber.length() > MAX_INPUT_LENGTH_XXS, pointer, "validation.charCount.invalid.50");
        }
    }
}
