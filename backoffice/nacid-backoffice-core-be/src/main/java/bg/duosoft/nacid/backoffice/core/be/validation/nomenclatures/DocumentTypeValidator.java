package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeAppStatusDetailDTO;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseIntegerKeyNomenclatureValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DocumentTypeFilterDTO;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentTypeValidator extends BaseIntegerKeyNomenclatureValidator<DocumentTypeDTO, DocumentTypeFilterDTO> {
    @Override
    protected void validateAdditional(List<ValidationError> errors, DocumentTypeDTO obj, Object... args) {
        rejectIfTrue(errors, !StringUtils.hasText(obj.getDirection()), "direction", "validation.field.required");
        if (StringUtils.hasText(obj.getDirection())) {
            rejectIfTrue(errors, obj.getDirection().length() > 1, "direction", "validation.charCount.invalid.1");
        }

        if (!CollectionUtils.isEmpty(obj.getStatuses())) {
            for (DocumentTypeAppStatusDetailDTO status : obj.getStatuses()) {
                rejectIfTrue(errors, Objects.isNull(status.getApplicationType())
                        || Objects.isNull(status.getApplicationType().getId()), "applicationType.id", "validation.field.required");
                rejectIfTrue(errors, Objects.isNull(status.getStatus())
                        || Objects.isNull(status.getStatus().getId()), "status.id", "validation.field.required");
            }
        }


        if (!CollectionUtils.isEmpty(obj.getDetails())) {
            for (DocumentTypeDetailDTO detail : obj.getDetails()) {
                rejectIfTrue(errors, Objects.isNull(detail.getDocumentCategory())
                        || Objects.isNull(detail.getDocumentCategory().getId()), "documentCategory.id", "validation.field.required");

                if (Objects.nonNull(detail.getApplicationSubtype()) && Objects.nonNull(detail.getApplicationSubtype().getId())) {
                    rejectIfTrue(errors, Objects.isNull(detail.getApplicationType())
                            || Objects.isNull(detail.getApplicationType().getId()), "documentCategory.applicationType", "validation.field.required");
                }
            }
        }
    }
}
