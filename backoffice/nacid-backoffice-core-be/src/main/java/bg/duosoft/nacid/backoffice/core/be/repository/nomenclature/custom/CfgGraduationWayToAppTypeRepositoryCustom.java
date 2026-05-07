package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgGraduationWayToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgGraduationWayToAppTypeFilterDTO;

import java.util.List;

public interface CfgGraduationWayToAppTypeRepositoryCustom {
    List<CfgGraduationWayToAppTypeEntity> selectData(CfgGraduationWayToAppTypeFilterDTO filter);

    int countData(CfgGraduationWayToAppTypeFilterDTO filter);
}
