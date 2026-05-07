package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.DictionarySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DictionaryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DictionaryFilterDTO;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.NomenclatureSearchRepositoryImpl;

public class DictionarySearchRepositoryImpl extends NomenclatureSearchRepositoryImpl<String, DictionaryEntity, DictionaryFilterDTO> implements DictionarySearchRepository {
    @Override
    protected Class<DictionaryEntity> getEntityClass() {
        return DictionaryEntity.class;
    }
}
