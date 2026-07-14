package bg.duosoft.nacid.backoffice.core.data.validation.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacidshareddata.validation.config.DefaultValidation;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

public interface MethodReceiveMethodValidator extends DefaultValidation {

    default void validateCrfCode(ApplicationDTO application, List<ValidationError> errors) {
        List<ApplicationDocumentReceiveMethodDTO> documentReceiveMethods = application.getDocumentReceiveMethods();
        rejectIfEmptyCollection(errors, documentReceiveMethods, "documentReceiveMethod.crfCodes", "validation.field.required");
    }

    default void validateCrfCodeAndReceiveMethod(ApplicationDTO application, List<ValidationError> errors) {
        List<ApplicationDocumentReceiveMethodDTO> documentReceiveMethods = application.getDocumentReceiveMethods();
        rejectIfEmptyCollection(errors, documentReceiveMethods, "documentReceiveMethod.crfCodes", "validation.field.required");
        if (!CollectionUtils.isEmpty(documentReceiveMethods)) {
            for (ApplicationDocumentReceiveMethodDTO documentReceiveMethodDTO : documentReceiveMethods) {
                String crfCodeMessagePointer = Objects.nonNull(documentReceiveMethodDTO.getCrfCode())?documentReceiveMethodDTO.getCrfCode().getId():"";
                rejectIfTrue(errors, Objects.isNull(documentReceiveMethodDTO.getDocumentReceiveMethod()) || Objects.isNull(documentReceiveMethodDTO.getDocumentReceiveMethod().getId()), "documentReceiveMethod" + crfCodeMessagePointer, "validation.field.required");
            }

        }
    }
}
