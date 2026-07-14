package bg.duosoft.nacid.backoffice.rudi.be.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;

public interface ApplicationDataService {

    void fillFullPersonAndAddressData(RudiApplicationDTO rudiApplication);

}
