package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationSarStatusService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiApplicationService;
import bg.duosoft.nacid.backoffice.rudi.be.service.RudiStatusService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.application.ValidationScope;
import bg.duosoft.nacidbackofficeshareddata.service.BaseStatusService;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RudiStatusServiceImpl implements RudiStatusService {
    private final BaseStatusService baseStatusService;
    private final ApplicationSarStatusService applicationSarStatusService;
    private final RudiApplicationService rudiApplicationService;

    @Override
    public RudiApplicationDTO insertRudiStatus(Integer applicationId, InsertStatusDTO insertStatus) {
        InsertStatusResultDTO insertStatusResult = baseStatusService.insertStatus(insertStatus);
        RudiApplicationDTO rudiApplication = rudiApplicationService.selectById(applicationId);

        if (Objects.isNull(rudiApplication)) {
            throw new ResourceNotFoundException("Application not found ! ID: " + applicationId);
        }

        ApplicationSubType applicationSubType = ApplicationSubType.selectByTypeAndSubType(rudiApplication.getApplication().getApplicationType().getId(), rudiApplication.getApplication().getApplicationSubtype().getId());
        if (applicationSubType == ApplicationSubType.RUDI_SAR) {
            applicationSarStatusService.fillFinalSarStatuses(insertStatusResult, rudiApplication);
            return rudiApplicationService.save(rudiApplication, ValidationScope.NO_VALIDATION);
        }

        return rudiApplication;
    }
}
