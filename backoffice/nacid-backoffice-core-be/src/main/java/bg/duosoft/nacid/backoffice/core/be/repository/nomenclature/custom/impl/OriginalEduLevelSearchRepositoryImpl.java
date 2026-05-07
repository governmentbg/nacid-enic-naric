package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.OriginalEduLevelSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VOriginalEduLevelEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class OriginalEduLevelSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements OriginalEduLevelSearchRepository {
    private static final String ORIGINAL_EDU_LEVEL_COLUMN = "originalEduLevelName";

    @Override
    public List<String> selectOriginalEduLevels(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return ORIGINAL_EDU_LEVEL_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VOriginalEduLevelEntity.class;
    }
}
