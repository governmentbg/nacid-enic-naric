package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CertificateReceiveFormType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.DocumentReceiveMethod;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.util.common.ReferenceDataUtils;
import bg.duosoft.nacid.backoffice.core.data.validation.base.ApplicantDiplomaNamesValidator;
import bg.duosoft.nacid.backoffice.core.data.validation.base.ApplicationNotesValidator;
import bg.duosoft.nacid.backoffice.core.data.validation.base.MethodReceiveMethodValidator;
import bg.duosoft.nacid.backoffice.core.data.validation.base.RepresentativeValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainDataBaseValidator implements Validator<RudiApplicationDTO>, ApplicantDiplomaNamesValidator, RepresentativeValidator, ApplicationNotesValidator, MethodReceiveMethodValidator {

    @Override
    public List<ValidationError> validate(RudiApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        ApplicationDTO application = obj.getApplication();
        rejectIfEmpty(errors, application.getApplicant(), "applicant", "validation.field.required");
        validateApplicantDiplomaNames(errors, application);
        validateApplicationNotes(errors, application);
        validateCertificateReceiveMethod(application, errors);
        return errors;
    }

    protected void validateCertificateReceiveMethod(ApplicationDTO application, List<ValidationError> errors) {
        validateCrfCodeAndReceiveMethod(application,errors);
    }
}
