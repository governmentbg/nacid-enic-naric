package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InitialConstraintDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.RudiStatusDataBaseDTO;

public interface ApplicationStatusService {
    InitialConstraintDTO examineStatusInitialData(RudiApplicationDTO application);

    RudiStatusDataBaseDTO insertStatus(RudiStatusDataBaseDTO statusDataSection);
}
