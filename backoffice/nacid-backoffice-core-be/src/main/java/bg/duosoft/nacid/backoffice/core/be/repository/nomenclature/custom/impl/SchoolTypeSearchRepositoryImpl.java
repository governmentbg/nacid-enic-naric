package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SchoolTypeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSchoolTypeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class SchoolTypeSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements SchoolTypeSearchRepository {
    private static final String SCHOOL_TYPE_COLUMN = "schoolType";

    @Override
    public List<String> selectSchoolTypes(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return SCHOOL_TYPE_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VSchoolTypeEntity.class;
    }

}
