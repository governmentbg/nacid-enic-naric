package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureSearchBaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ProfGroupEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfGroupFilterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2022
 * Time: 15:03
 */
public interface ProfGroupSearchRepository extends NomenclatureSearchBaseRepository<Integer, ProfGroupEntity, ProfGroupFilterDTO> {
}
