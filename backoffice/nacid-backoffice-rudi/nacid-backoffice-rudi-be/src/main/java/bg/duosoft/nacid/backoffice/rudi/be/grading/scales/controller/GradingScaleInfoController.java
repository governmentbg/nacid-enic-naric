package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.controller;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.GradingScaleInfoDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.GradingScaleInfoService;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.validator.ScaleValidator;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.keycloak.common.util.CollectionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/info")
public class GradingScaleInfoController {

    private final GradingScaleInfoService gradingScaleInfoService;
    private final ScaleValidator scaleValidator;

    @GetMapping("/{gradingScaleId}")
    public List<GradingScaleInfoDto> getGradingScaleInfo(@PathVariable("gradingScaleId") Integer gradingScaleId) {

        List<ValidationError> errors = scaleValidator.validate(gradingScaleId);

        if (!CollectionUtil.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }


        return this.gradingScaleInfoService.getGradingScaleInfo(gradingScaleId);
    }
}
