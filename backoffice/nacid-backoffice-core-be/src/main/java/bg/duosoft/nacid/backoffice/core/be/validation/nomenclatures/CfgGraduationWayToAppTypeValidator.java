package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgGraduationWayToApplicationTypeService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgGraduationWayToAppTypeDTO;
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
public class CfgGraduationWayToAppTypeValidator implements Validator<CfgGraduationWayToAppTypeDTO> {
    @Override
    public List<ValidationError> validate(CfgGraduationWayToAppTypeDTO obj, Object... args) {
        CfgGraduationWayToApplicationTypeService service = (CfgGraduationWayToApplicationTypeService) args[1];
        List<ValidationError> errors = new ArrayList<>();

        rejectIfTrue(errors, Objects.isNull(obj.getGraduationWay()) || !StringUtils.hasText(obj.getGraduationWay().getId()), "graduationWay.id", "validation.field.required");
        rejectIfTrue(errors, Objects.isNull(obj.getApplicationType()) || !StringUtils.hasText(obj.getApplicationType().getId()), "applicationType.id", "validation.field.required");
        rejectIfTrue(errors, Objects.isNull(obj.getApplicationSubtype()) || !StringUtils.hasText(obj.getApplicationSubtype().getId()), "applicationSubtype.id", "validation.field.required");

        if (Objects.nonNull(obj.getGraduationWay()) && StringUtils.hasText(obj.getGraduationWay().getId()) &&
                Objects.nonNull(obj.getApplicationType()) && StringUtils.hasText(obj.getApplicationType().getId()) &&
                Objects.nonNull(obj.getApplicationSubtype()) && StringUtils.hasText(obj.getApplicationSubtype().getId())) {
            CfgGraduationWayToAppTypeDTO cfgGraduationWayToAppTypeDTO = service.selectById(obj.getGraduationWay().getId(), obj.getApplicationType().getId(), obj.getApplicationSubtype().getId());
            if (Objects.nonNull(cfgGraduationWayToAppTypeDTO)) {
                reject(errors, "existingRecord", "m.existing.config");
            }
        }
        return errors;
    }
}
