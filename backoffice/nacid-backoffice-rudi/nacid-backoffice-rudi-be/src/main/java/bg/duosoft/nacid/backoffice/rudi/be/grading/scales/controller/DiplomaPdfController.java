package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.controller;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaDetailsDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.DiplomaPdfService;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.validator.DiplomaDetailsValidator;
import bg.duosoft.nacidshareddata.exception.ValidationErrorException;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.keycloak.common.util.CollectionUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pdf")
public class DiplomaPdfController {

    private final DiplomaPdfService diplomaPdfService;
    private final DiplomaDetailsValidator diplomaDetailsValidator;


    @PostMapping
    public ResponseEntity<byte[]> getDiplomaPdf(@RequestBody DiplomaDetailsDto diplomaDetailsDto) {

        List<ValidationError> errors = this.diplomaDetailsValidator.validate(diplomaDetailsDto);
        if (!CollectionUtil.isEmpty(errors)) {
            throw new ValidationErrorException(errors);
        }

        return this.diplomaPdfService.createPdfFile(diplomaDetailsDto);
    }
}
