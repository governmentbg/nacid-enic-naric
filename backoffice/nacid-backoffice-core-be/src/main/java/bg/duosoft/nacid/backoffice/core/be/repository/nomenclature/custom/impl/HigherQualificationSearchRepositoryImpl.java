package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.HigherQualificationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VHigherQualificationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class HigherQualificationSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements HigherQualificationSearchRepository {
    private static final String HIGHER_QUALIFICATION_COLUMN = "higherQualification";

    @Override
    public List<String> selectHigherQualifications(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return HIGHER_QUALIFICATION_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VHigherQualificationEntity.class;
    }
}
