package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;

public interface PersonManagementService {

    PersonDTO processPersonSaving(PersonDTO person, Boolean createNewVersion);

}
