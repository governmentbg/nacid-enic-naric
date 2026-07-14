package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.be.validation.common.PersonValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonSearchDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonSearchResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.LegalEntityFilterDTO;

import java.util.List;

public interface PersonService {
    PersonDTO selectById(Integer id);

    List<PersonDTO> selectByCivilId(String civilIdType, String civilId, String foreignIdentifierType, String foreignIdentifierCountry, Boolean isActive);

    List<PersonSearchResultDTO> searchForApplicationsUse(PersonSearchDTO searchCriteria, int maxResults);

    List<PersonDTO> searchLegalApplicants(LegalEntityFilterDTO filter);

    List<PersonDTO> searchLegalEntities(LegalEntityFilterDTO filter);

    List<PersonDTO> searchRepresentativeCompanies(LegalEntityFilterDTO filter);

    PersonDTO save(PersonDTO personDTO, PersonValidator validator);

    List<PersonDTO> searchRecords(PersonFilterDTO filter, boolean withAppsCount);

    int getRecordsCount(PersonFilterDTO filter);

    void deletePerson(Integer personId);

}
