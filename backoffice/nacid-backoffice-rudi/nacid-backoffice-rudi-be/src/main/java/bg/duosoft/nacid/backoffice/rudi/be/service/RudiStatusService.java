package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.InsertStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;

public interface RudiStatusService {
    RudiApplicationDTO insertRudiStatus(Integer applicationId, InsertStatusDTO insertStatus);
}
