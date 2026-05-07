package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacidbackofficeshareddata.repository.NomenclatureBaseRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom.DictionarySearchRepository;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.DictionaryEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.DictionaryFilterDTO;

public interface DictionaryRepository extends NomenclatureBaseRepository<String, DictionaryEntity, DictionaryFilterDTO>, DictionarySearchRepository {
}
