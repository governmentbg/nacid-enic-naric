package bg.duosoft.nacid.backoffice.rudi.be.validator.application;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.common.*;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.docrec.DocrecValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.sar.SarValidator;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.udirec.UdirecValidator;
import bg.duosoft.nacidbackofficeshareddata.exception.AppTypeNotPresentedException;
import bg.duosoft.nacidbackofficeshareddata.validator.ApplicationAttachmentsValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RudiApplicationValidator implements Validator<RudiApplicationDTO> {

    private final SarValidator sarValidator;
    private final UdirecValidator uniDiplomaRecValidator;
    private final DocrecValidator docDegreeRecValidator;
    private final ComissionCalendarProcessDataValidator rudiAppComCalendarProcessDataValidator;
    private final ApplicationResponsibleUserValidator applicationResponsibleUserValidator;
    private final ApplicationAttachmentsValidator applicationAttachmentsValidator;
    private final AppCommissionMemberDataValidator appCommissionMemberDataValidator;
    private final AppCommissionMemberStatementDataValidator appCommissionMemberStatementDataValidator;

    @Override
    public List<ValidationError> validate(RudiApplicationDTO obj, Object... args) {
        List<ValidationError> errors = new ArrayList<>();

        ValidationScope validationScope = getValidationScope(args);
        if (ValidationScope.NO_VALIDATION == validationScope) {
            return null;
        }

        if (ValidationScope.COMMISSION_CALENDAR_PROCESS_DATA == validationScope) {
            return rudiAppComCalendarProcessDataValidator.validate(obj);
        }
        if (ValidationScope.RESPONSIBLE_USER == validationScope) {
            return applicationResponsibleUserValidator.validate(obj);
        }
        if (ValidationScope.APP_ATTACHMENTS == validationScope) {
            return applicationAttachmentsValidator.validate(obj.getApplication());
        }
        if (ValidationScope.COMMISSION_MEMBER == validationScope) {
            return appCommissionMemberDataValidator.validate(obj);
        }
        if (ValidationScope.COMMISSION_MEMBER_STATEMENT == validationScope) {
            return appCommissionMemberStatementDataValidator.validate(obj);
        }

        ApplicationSubType type = ApplicationSubType.selectByTypeAndSubType(getType(obj), getSubType(obj));
        switch (type) {
            case RUDI_SAR -> sarValidator.validate(errors, obj, validationScope);
            case RUDI_DOC_DEGREE_RECOGNITION -> docDegreeRecValidator.validate(errors, obj, validationScope);
            case RUDI_UNI_DIPLOMA_RECOGNITION -> uniDiplomaRecValidator.validate(errors, obj, validationScope);
        }

        return errors;
    }

    private String getType(RudiApplicationDTO obj) {
        try {
            return obj.getApplication().getApplicationType().getId();
        } catch (Exception e) {
            throw new AppTypeNotPresentedException("Application type is not present for rudi application !", e);
        }
    }

    private String getSubType(RudiApplicationDTO obj) {
        try {
            return obj.getApplication().getApplicationSubtype().getId();
        } catch (Exception e) {
            throw new AppTypeNotPresentedException("Application subtype is not present for rudi application !", e);
        }
    }

    private ValidationScope getValidationScope(Object... args) {
        ValidationScope validationScope = ValidationScope.FINAL;

        try {
            validationScope = (ValidationScope) args[0];
        } catch (Exception e) {
            log.warn("Rudi app validation scope is not present! Use RudiValidationScope.ALL as default !");
        }

        return validationScope;
    }
}
