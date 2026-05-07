package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SchoolGradeSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSchoolGradeEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class SchoolGradeSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements SchoolGradeSearchRepository {
    private static final String SCHOOL_GRADE_COLUMN = "schoolGrade";

    @Override
    public List<String> selectSchoolGrades(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return SCHOOL_GRADE_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VSchoolGradeEntity.class;
    }

}
