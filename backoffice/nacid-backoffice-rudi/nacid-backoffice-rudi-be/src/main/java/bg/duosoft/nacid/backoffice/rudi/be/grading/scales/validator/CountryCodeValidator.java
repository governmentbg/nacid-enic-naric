package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.validator;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.CountryDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.CountriesService;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CountryCodeValidator implements Validator<String> {

    private final CountriesService countriesService;

    @Override
    public List<ValidationError> validate(String obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();
        rejectIfTrue(errors, Objects.isNull(obj), "country", "countryCode.empty");
        if (Objects.nonNull(obj)) {
            rejectIfFalse(errors, this.countriesService
                    .getActiveCountries()
                    .stream()
                    .map(CountryDto::getCode)
                    .toList()
                    .contains(obj), "countryCode", "countryCode.notFound");
        }

        return errors;
    }
}
