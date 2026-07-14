package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.OriginalQualificationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VOriginalQualificationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class OriginalQualificationSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements OriginalQualificationSearchRepository {
    private static final String ORIGINAL_QUALIFICATION_COLUMN = "originalQualification";

    @Override
    public List<String> selectOriginalQualifications(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return ORIGINAL_QUALIFICATION_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VOriginalQualificationEntity.class;
    }
}
