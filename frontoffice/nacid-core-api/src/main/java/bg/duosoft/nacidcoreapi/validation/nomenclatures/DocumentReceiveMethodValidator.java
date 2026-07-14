package bg.duosoft.nacidcoreapi.validation.nomenclatures;

import bg.duosoft.nacidcoreapi.validation.nomenclatures.base.BaseNomenclatureValidator;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveMethodDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.DocumentReceiveMethodDataFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentReceiveMethodValidator extends BaseNomenclatureValidator<String, DocumentReceiveMethodDTO, DocumentReceiveMethodDataFilterDTO> {

    @Override
    protected Integer getIdLength() {
        return 50;
    }

    @Override
    protected Integer getNameLength() {
        return 2000;
    }

    @Override
    protected void validateAdditional(List<ValidationError> errors, DocumentReceiveMethodDTO obj, Object... args) {
        rejectIfEmptyBoolean(errors, obj.getDocumentRecipient(), "documentRecipient", "validation.field.required");
    }
}
