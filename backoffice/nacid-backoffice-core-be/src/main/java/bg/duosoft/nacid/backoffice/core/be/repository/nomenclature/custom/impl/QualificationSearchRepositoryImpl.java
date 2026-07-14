package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.QualificationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VQualificationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class QualificationSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements QualificationSearchRepository {
    private static final String QUALIFICATION_COLUMN = "qualification";

    @Override
    public List<String> selectQualifications(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return QUALIFICATION_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VQualificationEntity.class;
    }
}
