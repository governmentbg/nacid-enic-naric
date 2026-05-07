package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;

import java.util.List;

public interface ReferenceDataClientService {

    List<ReferenceDataDTO> selectAllByDomain(ReferenceDataDomain domain);

}
