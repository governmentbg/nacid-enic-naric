package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgServiceTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgServiceTypeFilterDTO;

import java.util.List;

public interface CfgServiceTypeRepositoryCustom {
    List<CfgServiceTypeEntity> selectServiceTypeData(CfgServiceTypeFilterDTO filter);
    int countServiceTypeData(CfgServiceTypeFilterDTO filter);
}
