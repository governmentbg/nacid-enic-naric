package bg.duosoft.nacid.backoffice.rudi.be.validator.application.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommissionApplicationService;
import bg.duosoft.nacidbackofficeshareddata.validator.BaseApplicationReportValidator;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RudiApplicationReportValidator extends BaseApplicationReportValidator {
    private final CommissionApplicationService commissionApplicationService;

    //Certificate validations
    @Override
    public void validateCertificate(List<ValidationError> errors, ApplicationDTO application) {
        if (application.getApplicationSubtype().getId().equals(ApplicationSubType.RUDI_UNI_DIPLOMA_RECOGNITION.appSubType())) {
            checkForValidCommissionCalendar(errors, application);
        }
    }

    private void checkForValidCommissionCalendar(List<ValidationError> errors, ApplicationDTO application) {
        List<CommissionApplicationDTO> commissionApplicationDTOList = commissionApplicationService.selectByApplicationId(application.getId());
        rejectIfTrue(errors, CollectionUtils.isEmpty(commissionApplicationDTOList), DOCUMENT_TYPE_POINTER, "validation.certificate.not.contained.in.calendar");
    }
    ////////////////////////////////////////////////
}
