package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SchoolAgeRangeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSchoolAgeRangeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class SchoolAgeRangeSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements SchoolAgeRangeSearchRepository {
    private static final String SCHOOL_AGE_RANGE_COLUMN = "schoolAgeRange";

    @Override
    public List<String> selectSchoolAgeRanges(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return SCHOOL_AGE_RANGE_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VSchoolAgeRangeEntity.class;
    }

}
