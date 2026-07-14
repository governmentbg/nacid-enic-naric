package bg.duosoft.nacid.backoffice.core.be.validation.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonUniversityAdditionalDetailsDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.CommonUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonUniversityAdditionalDetailsValidator implements Validator<PersonUniversityAdditionalDetailsDTO> {

    @Override
    public List<ValidationError> validate(PersonUniversityAdditionalDetailsDTO dto, Object... additionalArgs) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfTrue(errors, StringUtils.hasText(dto.getLetterGreeting()) && dto.getLetterGreeting().length() > 150, "letterGreeting", "m.validation.tooLongText");
        rejectIfTrue(errors, (StringUtils.hasText(dto.getLetterGreeting()) && !StringUtils.hasText(dto.getLetterRecipient())) || (!StringUtils.hasText(dto.getLetterGreeting()) && StringUtils.hasText(dto.getLetterRecipient())), "both.or.none.mandatory", "m.validation.both.or.none.mandatory");
        return errors;
    }

}
