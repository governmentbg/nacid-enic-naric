package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom;


import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ReferenceDataFilterDTO;

import java.util.List;

public interface ReferenceDataRepositoryCustom {

    List<ReferenceDataEntity> selectFoReferenceData(ReferenceDataFilterDTO filter);

    int selectFoReferenceDataCount(ReferenceDataFilterDTO filter);

}
