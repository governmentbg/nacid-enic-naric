package bg.duosoft.nacid.backoffice.core.be.repository.common.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.PersonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonSearchDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.LegalEntityFilterDTO;

import java.util.List;

public interface PersonSearchRepository {
    List<Object[]> searchForApplicationsUse(PersonSearchDTO searchCriteria, int maxResults);

    List<PersonEntity> searchLegalApplicants(LegalEntityFilterDTO filter);

    List<PersonEntity> searchLegalEntities(LegalEntityFilterDTO filter);

    List<PersonEntity> searchRepresentativeCompanies(LegalEntityFilterDTO filter);

    List<PersonEntity> searchRecords(PersonFilterDTO filter);

    int getRecordsCount(PersonFilterDTO filter);

}
