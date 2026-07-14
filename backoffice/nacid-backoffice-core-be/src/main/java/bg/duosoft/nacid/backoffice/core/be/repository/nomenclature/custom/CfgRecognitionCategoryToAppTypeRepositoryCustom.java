package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgRecognitionCategoryToAppTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgRecognitionCategoryToAppTypeFilterDTO;

import java.util.List;

public interface CfgRecognitionCategoryToAppTypeRepositoryCustom {
    List<CfgRecognitionCategoryToAppTypeEntity> selectData(CfgRecognitionCategoryToAppTypeFilterDTO filter);

    int countData(CfgRecognitionCategoryToAppTypeFilterDTO filter);
}
