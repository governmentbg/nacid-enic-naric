package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.HigherSpecialitySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VHigherSpecialityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class HigherSpecialitySearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements HigherSpecialitySearchRepository {
    private static final String HIGHER_SPECIALITY_COLUMN = "higherSpeciality";

    @Override
    public List<String> selectHigherSpecialities(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return HIGHER_SPECIALITY_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VHigherSpecialityEntity.class;
    }

}
