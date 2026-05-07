package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;

public interface ApplicationSarStatusService {
    void fillFinalSarStatuses(InsertStatusResultDTO insertStatusResult, RudiApplicationDTO application);
}
