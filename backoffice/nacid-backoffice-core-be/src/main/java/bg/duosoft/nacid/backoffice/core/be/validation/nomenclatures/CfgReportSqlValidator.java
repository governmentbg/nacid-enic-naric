package bg.duosoft.nacid.backoffice.core.be.validation.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportFieldRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportSqlRepository;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgReportFieldService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgReportSqlService;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportFieldEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgReportFieldDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgReportSqlDTO;
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
public class CfgReportSqlValidator implements Validator<CfgReportSqlDTO> {

    private final CfgReportFieldService cfgReportFieldService;

    @Override
    public List<ValidationError> validate(CfgReportSqlDTO obj, Object... args) {
        boolean isCreate = (Boolean) args[0];
        CfgReportSqlService reportSqlService = (CfgReportSqlService) args[1];
        List<ValidationError> errors = new ArrayList<>();
        boolean hasReportSqlId = StringUtils.hasText(obj.getId());

        rejectIfTrue(errors, !hasReportSqlId, "id", "validation.field.required");
        if (hasReportSqlId) {
            rejectIfTrue(errors, obj.getId().length() > 50, "bgName", "validation.charCount.invalid.50");
        }

        if (isCreate && hasReportSqlId) {
            CfgReportSqlDTO cfgReportSqlDTO = reportSqlService.selectById(obj.getId());
            rejectIfTrue(errors, Objects.nonNull(cfgReportSqlDTO), "id", "validation.report.already.exist");
        }

        rejectIfTrue(errors, !StringUtils.hasText(obj.getDescription()), "description", "validation.field.required");
        if (StringUtils.hasText(obj.getDescription())) {
            rejectIfTrue(errors, obj.getDescription().length() > 255, "description", "validation.charCount.invalid.255");
        }

        rejectIfTrue(errors, !StringUtils.hasText(obj.getSqlExpression()), "sqlExpression", "validation.field.required");
        rejectIfEmptyBoolean(errors, obj.getManyRowsFlag(), "manyRowsFlag", "validation.field.required");
        rejectIfEmptyBoolean(errors, obj.getGroupFlag(), "groupFlag", "validation.field.required");

        rejectIfTrue(errors, CollectionUtils.isEmpty(obj.getFields()), "sqlExpression", "validation.field.required");


        boolean existingField = false;
        if (!CollectionUtils.isEmpty(obj.getFields())) {

            for (CfgReportFieldDTO field : obj.getFields()) {
                rejectIfTrue(errors, !StringUtils.hasText(field.getId()), "field.id", "validation.field.required");

                if (StringUtils.hasText(field.getId())) {
                    rejectIfTrue(errors, field.getId().length() > 50, "field.id", "validation.charCount.invalid.50");
                    if (hasReportSqlId && !existingField){
                        existingField = cfgReportFieldService.isFieldExist(field.getId(), obj.getId());
                    }
                }

            }

        }
        rejectIfTrue(errors, existingField, "sqlExpression", "validation.report.field.already.exist");
        return errors;
    }
}
