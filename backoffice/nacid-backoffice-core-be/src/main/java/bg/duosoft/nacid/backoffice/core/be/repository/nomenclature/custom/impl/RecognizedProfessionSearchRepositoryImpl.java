package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.RecognizedProfessionSearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.VRecognizedProfessionEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.AutocompleteViewRepositoryImpl;

import java.util.List;

public class RecognizedProfessionSearchRepositoryImpl extends AutocompleteViewRepositoryImpl implements RecognizedProfessionSearchRepository {
    private static final String RECOGNIZED_PROFESSION_COLUMN = "recognizedProfession";

    @Override
    public List<String> selectRecognizedProfessions(AutocompleteViewFilterDTO filter) {
        return selectRecords(filter);
    }

    @Override
    public String getColumnName() {
        return RECOGNIZED_PROFESSION_COLUMN;
    }

    @Override
    public Class getTargetClass() {
        return VRecognizedProfessionEntity.class;
    }


}
