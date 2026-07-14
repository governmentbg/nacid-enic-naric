package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.SdkQualificationSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VSdkQualificationEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class SdkQualificationSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements SdkQualificationSearchRepository {
    private static final String SDK_QUALIFICATION_COLUMN = "sdkQualification";

    @Override
    public List<String> selectSdkQualifications(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return SDK_QUALIFICATION_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VSdkQualificationEntity.class;
    }
}
