package bg.duosoft.nacid.backoffice.core.data.validation.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalType;
import bg.duosoft.nacidshareddata.validation.config.DefaultValidation;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;

import java.util.List;
import java.util.Objects;

public interface RepresentativeValidator extends DefaultValidation {

    default void validateRepresentativeLegalType(List<ValidationError> errors, ApplicationDTO application) {
        PersonDTO representative = application.getRepresentative();
        if (Objects.nonNull(representative)) {
            LegalType legalType = LegalType.selectByCode(representative.getLegalType().getId());
            rejectIfTrue(errors, legalType != LegalType.NATURAL_PERSON, "representative", "validation.invalidLegalType");
        }
    }
}
