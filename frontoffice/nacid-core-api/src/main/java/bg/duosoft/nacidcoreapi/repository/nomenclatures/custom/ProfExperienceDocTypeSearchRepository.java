package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureSearchBaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ProfExperienceDocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfExperienceDocTypeFilterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.10.2022
 * Time: 17:30
 */
public interface ProfExperienceDocTypeSearchRepository extends NomenclatureSearchBaseRepository<String, ProfExperienceDocTypeEntity, ProfExperienceDocTypeFilterDTO> {
}
