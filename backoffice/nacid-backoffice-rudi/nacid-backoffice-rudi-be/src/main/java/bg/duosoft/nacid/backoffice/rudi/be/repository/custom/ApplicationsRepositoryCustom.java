package bg.duosoft.nacid.backoffice.rudi.be.repository.custom;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiApplicationsEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.RudiApplicationsFilterDTO;

import java.util.List;

public interface ApplicationsRepositoryCustom {
    List<VRudiApplicationsEntity> searchRecords(RudiApplicationsFilterDTO filter);
    List<Integer> selectApplicationIds(RudiApplicationsFilterDTO filter);

    List<VRudiApplicationsEntity> selectApplicationsByIdsAndSort(List<Integer> ids, String sortColumn, Boolean ascOrder);
    int getRecordsCount(RudiApplicationsFilterDTO filter);
}
