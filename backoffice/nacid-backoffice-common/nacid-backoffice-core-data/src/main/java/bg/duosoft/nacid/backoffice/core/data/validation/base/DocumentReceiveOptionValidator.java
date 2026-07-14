package bg.duosoft.nacid.backoffice.core.data.validation.base;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveOptionDTO;
import bg.duosoft.nacidshareddata.validation.config.DefaultValidation;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

public interface DocumentReceiveOptionValidator extends DefaultValidation {

    default void validateSelectedDocumentReceiveOptions(ApplicationDTO application, List<ValidationError> errors) {
        List<ApplicationDocumentReceiveOptionDTO> documentReceiveOptions = application.getDocumentReceiveOptions();
        rejectIfEmptyCollection(errors, documentReceiveOptions, "originalDocumentReceiveOption.receiveOption.documentReceiveOption.id", "validation.field.required");
        if (!CollectionUtils.isEmpty(documentReceiveOptions)) {
            for (ApplicationDocumentReceiveOptionDTO option : documentReceiveOptions) {
                rejectIfTrue(errors, Objects.isNull(option.getDocumentReceiveOption()) || Objects.isNull(option.getDocumentReceiveOption().getId()), "originalDocumentReceiveOption.receiveOption.documentReceiveOption.id", "validation.field.required");
                if (Objects.nonNull(option.getDocumentReceiveOption()) && Objects.nonNull(option.getDocumentReceiveOption().getDocumentRecipient()) && Boolean.TRUE.equals(option.getDocumentReceiveOption().getDocumentRecipient())) {
                    rejectIfTrue(errors, Objects.isNull(option.getDocumentRecipientAddress()) || Objects.isNull(option.getDocumentRecipientAddress().getId()), "originalDocumentReceiveOption.receiveOption.documentRecipientAddress.id", "validation.field.required");

                }
            }
        }
    }
}
