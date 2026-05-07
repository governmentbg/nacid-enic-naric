package bg.duosoft.nacidbackofficeshareddata.validator.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseApplicationReportValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationReportValidator extends BaseApplicationReportValidator {
    @Override
    public void validateCertificate(List<ValidationError> errors, ApplicationDTO application) {
    }

}
