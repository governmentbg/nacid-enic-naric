package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.controller;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.GradingScaleDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.GradingScaleService;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.validator.CountryCodeValidator;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.keycloak.common.util.CollectionUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grading-scales")
public class GradingScaleController {

    private final GradingScaleService gradingScaleService;
    private final CountryCodeValidator countryCodeValidator;

    @GetMapping("/{countryCode}")
    public List<GradingScaleDto> getGradingScalesByCountryCodeAndYear(@PathVariable("countryCode") String countryCode, @RequestParam(value = "year", required = false) Integer year) {

        List<ValidationError> errors = countryCodeValidator.validate(countryCode);

        if (!CollectionUtil.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }

        return this.gradingScaleService.getGradingScalesByCountryCodeAndYear(countryCode, year);
    }
}
