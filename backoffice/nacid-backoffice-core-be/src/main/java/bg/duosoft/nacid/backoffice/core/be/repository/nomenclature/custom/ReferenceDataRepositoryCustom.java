package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.ReferenceDataFilterDTO;

import java.util.List;

public interface ReferenceDataRepositoryCustom {

    List<ReferenceDataEntity> selectReferenceData(ReferenceDataFilterDTO filter);

    int selectReferenceDataCount(ReferenceDataFilterDTO filter);

}
