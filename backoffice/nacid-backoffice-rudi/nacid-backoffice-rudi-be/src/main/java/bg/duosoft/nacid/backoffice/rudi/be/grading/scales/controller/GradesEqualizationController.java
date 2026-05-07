package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.controller;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaDetailsDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.EqualizationSubjectDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.GradesEqualizationService;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.validator.DiplomaDetailsValidator;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.keycloak.common.util.CollectionUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/equalization")
public class GradesEqualizationController {

    private final GradesEqualizationService gradesEqualizationService;
    private final DiplomaDetailsValidator diplomaDetailsValidator;

    @PostMapping
    public List<EqualizationSubjectDto> gradeEqualization(@RequestBody DiplomaDetailsDto diplomaDetails) {

        List<ValidationError> errors = this.diplomaDetailsValidator.validate(diplomaDetails);
        if (!CollectionUtil.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }

        diplomaDetails.getSubjects().forEach(subject ->
                subject.setSubjectGrade(normalizeGrade.apply(subject.getSubjectGrade()))
        );
        return this.gradesEqualizationService.gradeEqualization(diplomaDetails);
    }

    private Function<String, String> normalizeGrade = grade -> {
        if (grade == null) return null;
        return grade.trim().replace(",", ".");
    };
}
