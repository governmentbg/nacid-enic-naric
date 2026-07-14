package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureSearchBaseRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DictionaryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DictionaryFilterDTO;

public interface DictionarySearchRepository extends NomenclatureSearchBaseRepository<String, DictionaryEntity, DictionaryFilterDTO> {
}
