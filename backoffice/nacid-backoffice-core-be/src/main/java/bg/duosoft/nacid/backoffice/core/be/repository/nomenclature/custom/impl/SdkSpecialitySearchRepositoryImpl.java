package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SdkSpecialitySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSdkSpecialityEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class SdkSpecialitySearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements SdkSpecialitySearchRepository {
    private static final String SDK_SPECIALITY_COLUMN = "sdkSpeciality";

    @Override
    public List<String> selectSdkSpecialities(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return SDK_SPECIALITY_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VSdkSpecialityEntity.class;
    }

}
