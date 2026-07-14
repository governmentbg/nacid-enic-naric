package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SchoolSubjectSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSchoolSubjectEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class SchoolSubjectSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements SchoolSubjectSearchRepository {
    private static final String SCHOOL_SUBJECT_COLUMN = "schoolSubject";

    @Override
    public List<String> selectSchoolSubjects(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return SCHOOL_SUBJECT_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VSchoolSubjectEntity.class;
    }

}
