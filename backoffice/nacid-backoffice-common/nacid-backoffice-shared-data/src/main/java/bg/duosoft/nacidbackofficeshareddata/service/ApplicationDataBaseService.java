package bg.duosoft.nacidbackofficeshareddata.service;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
public interface ApplicationDataBaseService {
    void fillFullPersonAndAddressData(ApplicationDTO application);
}
