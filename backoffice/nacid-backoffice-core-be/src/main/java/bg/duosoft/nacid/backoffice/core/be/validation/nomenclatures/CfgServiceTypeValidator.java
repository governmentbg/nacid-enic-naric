package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgServiceTypeService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgServiceTypeDTO;
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
public class CfgServiceTypeValidator implements Validator<CfgServiceTypeDTO> {
    @Override
    public List<ValidationError> validate(CfgServiceTypeDTO obj, Object... args) {
        boolean isCreate = (Boolean) args[0];
        CfgServiceTypeService service = (CfgServiceTypeService) args[1];
        List<ValidationError> errors = new ArrayList<>();
        rejectIfTrue(errors, Objects.isNull(obj.getApplicationType()) || !StringUtils.hasText(obj.getApplicationType().getId()), "applicationType.id", "validation.field.required");
        rejectIfTrue(errors, Objects.isNull(obj.getServiceType()) || !StringUtils.hasText(obj.getServiceType().getId()), "serviceType.id", "validation.field.required");
        rejectIfTrue(errors, Objects.isNull(obj.getExecutionDaysType()) || !StringUtils.hasText(obj.getExecutionDaysType().getId()), "executionDaysType.id", "validation.field.required");
        rejectIfTrue(errors, Objects.nonNull(obj.getExecutionDays()) && obj.getExecutionDays() < 0, "executionDays", "validation.field.invalid");

        if (CollectionUtils.isEmpty(errors) && isCreate) {
            String applicationSubType = Objects.isNull(obj.getApplicationSubtype()) || !StringUtils.hasText(obj.getApplicationSubtype().getId()) ? null : obj.getApplicationSubtype().getId();
            List<CfgServiceTypeDTO> serviceTypes = service.selectByApplicationTypeAndSubType(obj.getApplicationType().getId(), applicationSubType);
            if (!CollectionUtils.isEmpty(serviceTypes)) {
                CfgServiceTypeDTO cfgServiceTypeDTO = serviceTypes.stream().filter(r -> {
                    String dbRecordApplicationSubType = Objects.isNull(r.getApplicationSubtype()) ? null : r.getApplicationSubtype().getId();
                    return Objects.equals(applicationSubType, dbRecordApplicationSubType) && (r.getServiceType().getId().equals(obj.getServiceType().getId()));
                }).findFirst().orElse(null);
                rejectIfTrue(errors, Objects.nonNull(cfgServiceTypeDTO), "existingRecordByTypeAndSubtype", "validation.cfg.service.type.already.exist");
            }
        }


        return errors;
    }
}
