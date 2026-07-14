package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;

import java.util.List;

public interface AppStatusClientService {

    List<CfgSarAppStatusDTO> selectAllSarStatusConfigs();

}
