package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.validator.BaseStringKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentReceiveMethodFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentReceiveMethodValidator extends BaseStringKeyNomenclatureValidator<DocumentReceiveMethodDTO, DocumentReceiveMethodFilterDTO> {

    @Override
    protected void validateAdditional(List<ValidationError> errors, DocumentReceiveMethodDTO obj, Object... args) {
        rejectIfEmptyBoolean(errors, obj.getHasDocumentRecipient(), "hasDocumentRecipient", "validation.field.required");
        rejectIfEmptyBoolean(errors, obj.getHasDocumentRecipient(), "eservicesRequirePaymentReceipt", "validation.field.required");
        rejectIfEmptyBoolean(errors, obj.getDefaultFlag(), "defaultFlag", "validation.field.required");
        rejectIfEmpty(errors, obj.getIndex(), "index", "validation.field.required");
    }

}
