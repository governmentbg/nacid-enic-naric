package bg.duosoft.nacid.backoffice.core.data.validation.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.CommonUtils;
import bg.duosoft.nacid.backoffice.core.data.validation.base.ApplicantDiplomaNamesValidator;
import bg.duosoft.nacid.backoffice.core.data.validation.base.CivilIdValidator;
import bg.duosoft.nacid.backoffice.core.data.validation.base.RepresentativeValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceptionBaseValidator implements Validator<ApplicationDTO>, ApplicantDiplomaNamesValidator, RepresentativeValidator, CivilIdValidator {


    //TODO Inactive persons
    @Override
    public List<ValidationError> validate(ApplicationDTO application, Object... objects) {
        List<ValidationError> errors = new ArrayList<>();

        List<DocumentReceiveMethodDTO> allDocReceiveMethods = (List<DocumentReceiveMethodDTO>) objects[0];

        rejectIfTrue(errors, Objects.nonNull(application.getId()), "applicationId", "validation.notEmptyIdentifier");
        rejectIfEmpty(errors, application.getApplicant(), "applicant", "validation.field.required");

        if (!application.getApplicationType().getId().equals(ApplicationType.LIBSERV.code())) {
            rejectIfEmpty(errors, application.getContactAddress(), "contactAddress", "validation.field.required");
        } else {
            errors.addAll(validateContactAddressDependentOnPersons(application));
        }

        validateApplicantDiplomaNames(errors, application);
        validateRepresentativeLegalType(errors, application);

        List<ApplicationDocumentReceiveMethodDTO> documentReceiveMethods = application.getDocumentReceiveMethods();

        if (!CollectionUtils.isEmpty(documentReceiveMethods)){
            for (ApplicationDocumentReceiveMethodDTO applicationDocumentReceiveMethod :documentReceiveMethods) {
                String crfCodeMessagePointer = Objects.nonNull(applicationDocumentReceiveMethod.getCrfCode())?applicationDocumentReceiveMethod.getCrfCode().getId():"";
                rejectIfTrue(errors,Objects.isNull(applicationDocumentReceiveMethod.getDocumentReceiveMethod()) || Objects.isNull(applicationDocumentReceiveMethod.getDocumentReceiveMethod().getId()),"documentReceiveMethod"+crfCodeMessagePointer,"validation.field.required");
                if (Objects.nonNull(applicationDocumentReceiveMethod.getDocumentReceiveMethod())) {
                    String documentReceiveMethodId = applicationDocumentReceiveMethod.getDocumentReceiveMethod().getId();
                    if (StringUtils.hasText(documentReceiveMethodId) && !CollectionUtils.isEmpty(allDocReceiveMethods)) {
                        Boolean hasDocumentRecipient = allDocReceiveMethods.stream()
                                .filter(d -> Objects.equals(d.getId(), documentReceiveMethodId))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Document receive method does not exist ! ID: " + documentReceiveMethodId)).getHasDocumentRecipient();

                        if (Objects.nonNull(hasDocumentRecipient) && hasDocumentRecipient) {
                            rejectIfTrue(errors, Objects.isNull(applicationDocumentReceiveMethod.getDocumentRecipientAddress()) || Objects.isNull(applicationDocumentReceiveMethod.getDocumentRecipientAddress().getId()), "documentRecipientAddressId", "validation.field.required");
                        }
                    }
                }
            }
        }
        return errors;
    }


    private List<ValidationError> validateContactAddressDependentOnPersons(ApplicationDTO application) {
        List<ValidationError> errors = new ArrayList<>();
        PersonDTO representative = application.getRepresentative();
        PersonDTO applicant = application.getApplicant();
        AddressDTO contactAddress = application.getContactAddress();
        if (Objects.isNull(contactAddress)) {
            rejectIfTrue(errors, Objects.nonNull(representative) && !StringUtils.hasText(representative.getEmail()), "representativeEmail", "validation.field.required");
            rejectIfTrue(errors, Objects.isNull(representative) && Objects.nonNull(applicant) && !StringUtils.hasText(applicant.getEmail()), "applicantEmail", "validation.field.required");
        }
        return errors;
    }

}
