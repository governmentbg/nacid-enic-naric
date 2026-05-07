package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUsersDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.NacidUserAutocompleteDTO;

import java.util.List;

public interface ApplicationResponsibleUsersService {
    List<ApplicationResponsibleUsersDTO> selectByApplicationId(Integer applicationId);
    ApplicationResponsibleUsersDTO selectMainResponsibleUserByApplicationId(Integer applicationId);
    List<NacidUserAutocompleteDTO> selectResponsibleUsersByGroup(String group);
}
