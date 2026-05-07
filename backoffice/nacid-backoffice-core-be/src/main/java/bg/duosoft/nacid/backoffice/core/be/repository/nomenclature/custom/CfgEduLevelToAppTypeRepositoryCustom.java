package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgEduLevelToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgEduLevelToAppTypeFilterDTO;

import java.util.List;

public interface CfgEduLevelToAppTypeRepositoryCustom {
    List<CfgEduLevelToAppTypeEntity> selectData(CfgEduLevelToAppTypeFilterDTO filter);

    int countData(CfgEduLevelToAppTypeFilterDTO filter);
}
