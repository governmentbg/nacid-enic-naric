package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.ImiCorrespondenceSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VImiCorrespondenceEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class ImiCorrespondenceSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements ImiCorrespondenceSearchRepository {
    private static final String IMI_CORRESPONDENCE_COLUMN = "imiCorrespondence";

    @Override
    public List<String> selectImiCorrespondences(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return IMI_CORRESPONDENCE_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VImiCorrespondenceEntity.class;
    }

}
