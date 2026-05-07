package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ExperienceProfessionSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VExperienceProfessionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class ExperienceProfessionSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements ExperienceProfessionSearchRepository {
    private static final String PROFESSION_NAME_COLUMN = "professionName";

    @Override
    public List<String> selectExperienceProfessions(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return PROFESSION_NAME_COLUMN;
    }

    @Override
    public Class getTargetClass() {
       return VExperienceProfessionEntity.class;
    }
}
