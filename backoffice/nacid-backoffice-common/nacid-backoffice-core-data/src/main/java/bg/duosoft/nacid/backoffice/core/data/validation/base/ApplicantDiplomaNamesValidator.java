package bg.duosoft.nacid.backoffice.core.data.validation.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.CivilIdType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacidshareddata.validation.config.DefaultValidation;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

public interface ApplicantDiplomaNamesValidator extends DefaultValidation, CivilIdValidator {

    default void validateApplicantDiplomaNames(List<ValidationError> errors, ApplicationDTO application) {
        ApplicantDiplomaNamesDTO applicantDiplomaNames = application.getApplicantDiplomaNames();
        if (Objects.nonNull(applicantDiplomaNames)) {
            rejectIfEmpty(errors, applicantDiplomaNames.getFirstName(), "applicantDiplomaNames.firstName", "validation.field.required");
            rejectIfEmpty(errors, applicantDiplomaNames.getLastName(), "applicantDiplomaNames.lastName", "validation.field.required");

            String civilId = applicantDiplomaNames.getCivilId();
            CivilIdTypeDTO civilIdTypeDTO = applicantDiplomaNames.getCivilIdType();
            if (Objects.nonNull(civilIdTypeDTO) && StringUtils.hasText(civilIdTypeDTO.getId())) {
                CivilIdType civilIdType = CivilIdType.selectByCode(civilIdTypeDTO.getId());
                validateCivilId(errors, civilIdType, civilId, "applicantDiplomaNames.civilId");
            }
        }
    }
}
