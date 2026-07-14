package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.validator;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaDetailsDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.GradingScaleService;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DiplomaDetailsValidator implements Validator<DiplomaDetailsDto> {
    private final GradingScaleService gradingScaleService;

    private final CountryCodeValidator countryCodeValidator;
    private final YearValidator yearValidator;
    private final ScaleValidator scaleValidator;
    private final DiplomaSubjetsValidator diplomaSubjectsValidator;

    @Override
    public List<ValidationError> validate(DiplomaDetailsDto obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfTrue(errors, Objects.isNull(obj), "diplomaDetails", "diplomaDetails.empty");
        if (Objects.nonNull(obj)) {

            errors.addAll(this.countryCodeValidator.validate(obj.getCountryCode()));

            errors.addAll(this.yearValidator.validate(obj.getYear()));

            errors.addAll(this.scaleValidator.validate(obj.getScalaId()));

            rejectIfFalse(errors, Objects.nonNull(obj.getSubjects()) && !obj.getSubjects().isEmpty(), "subjects", "diplomaDetails.yearAndCountryCode.empty");

            errors.addAll(this.diplomaSubjectsValidator.validate(obj.getSubjects()));


        }

        return errors;
    }

}
