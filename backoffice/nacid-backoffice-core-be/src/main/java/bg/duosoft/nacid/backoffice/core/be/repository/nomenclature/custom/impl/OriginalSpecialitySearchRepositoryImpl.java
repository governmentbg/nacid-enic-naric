package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.OriginalSpecialitySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VOriginalSpecialityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class OriginalSpecialitySearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements OriginalSpecialitySearchRepository {
    private static final String ORIGINAL_SPECIALITY_COLUMN = "originalSpeciality";

    @Override
    public List<String> selectOriginalSpecialities(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return ORIGINAL_SPECIALITY_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VOriginalSpecialityEntity.class;
    }

}
