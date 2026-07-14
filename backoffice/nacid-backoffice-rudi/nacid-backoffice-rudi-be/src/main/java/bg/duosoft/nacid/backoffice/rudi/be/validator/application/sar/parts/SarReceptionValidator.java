package bg.duosoft.nacid.backoffice.rudi.be.validator.application.sar.parts;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.SarApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.RudiReceptionBaseValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SarReceptionValidator extends RudiReceptionBaseValidator implements Validator<RudiApplicationDTO> {

    @Override
    public List<ValidationError> validate(RudiApplicationDTO rudiApplicationDTO, Object... objects) {
        List<ValidationError> errors = super.validate(rudiApplicationDTO);

        validateSarFlag(errors, rudiApplicationDTO);
        return errors;
    }

    private void validateSarFlag(List<ValidationError> errors, RudiApplicationDTO rudiApplicationDTO) {
        SarApplicationDTO sarApplication = rudiApplicationDTO.getSarApplication();

        boolean isEmptyStatuteFlag = Objects.isNull(sarApplication.getIsStatute()) || !sarApplication.getIsStatute();
        boolean isEmptyAuthenticityFlag = Objects.isNull(sarApplication.getIsAuthenticity()) || !sarApplication.getIsAuthenticity();
        boolean isEmptyRecommendationFlag = Objects.isNull(sarApplication.getIsRecommendation()) || !sarApplication.getIsRecommendation();
        rejectIfTrue(errors, isEmptyStatuteFlag && isEmptyAuthenticityFlag && isEmptyRecommendationFlag, "sarFlag", "validation.field.required");
    }
}
