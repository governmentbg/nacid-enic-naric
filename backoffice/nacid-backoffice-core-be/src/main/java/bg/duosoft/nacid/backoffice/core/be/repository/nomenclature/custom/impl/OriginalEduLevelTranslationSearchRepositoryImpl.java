package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.OriginalEduLevelTranslationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VOriginalEduLevelTranslatedEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class OriginalEduLevelTranslationSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements OriginalEduLevelTranslationSearchRepository {
    private static final String ORIGINAL_EDU_LEVEL_TRANSLATED_COLUMN = "originalEduLevelTranslated";

    @Override
    public List<String> selectOriginalEduLevelTranslations(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return ORIGINAL_EDU_LEVEL_TRANSLATED_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VOriginalEduLevelTranslatedEntity.class;
    }
}
