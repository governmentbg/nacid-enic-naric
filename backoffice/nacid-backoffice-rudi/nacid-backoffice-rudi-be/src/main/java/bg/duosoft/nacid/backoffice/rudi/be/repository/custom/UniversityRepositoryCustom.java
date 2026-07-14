package bg.duosoft.nacid.backoffice.rudi.be.repository.custom;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.UniversityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.UniversityFilterDTO;

import java.util.List;

public interface UniversityRepositoryCustom {
    List<UniversityEntity> searchRecords(UniversityFilterDTO filter);

    int getRecordsCount(UniversityFilterDTO filter);
}
