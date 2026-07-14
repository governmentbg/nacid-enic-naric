package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SpecialitySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSpecialityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class SpecialitySearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements SpecialitySearchRepository {
    private static final String SPECIALITY_COLUMN = "speciality";

    @Override
    public List<String> selectSpecialities(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return SPECIALITY_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VSpecialityEntity.class;
    }

}
