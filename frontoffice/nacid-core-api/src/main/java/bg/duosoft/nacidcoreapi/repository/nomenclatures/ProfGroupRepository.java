package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureBaseRepository;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.ProfGroupSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ProfGroupEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfGroupFilterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 15:00
 */
public interface ProfGroupRepository extends NomenclatureBaseRepository<Integer, ProfGroupEntity, ProfGroupFilterDTO>, ProfGroupSearchRepository {
}
