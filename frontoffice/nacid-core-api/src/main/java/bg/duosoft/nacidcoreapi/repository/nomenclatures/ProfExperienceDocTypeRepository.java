package bg.duosoft.nacidcoreapi.repository.nomenclatures;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureBaseRepository;
import bg.duosoft.nacidcoreapi.repository.nomenclatures.custom.ProfExperienceDocTypeSearchRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ProfExperienceDocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.ProfExperienceDocTypeFilterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.10.2022
 * Time: 17:29
 */
public interface ProfExperienceDocTypeRepository extends NomenclatureBaseRepository<String, ProfExperienceDocTypeEntity, ProfExperienceDocTypeFilterDTO>, ProfExperienceDocTypeSearchRepository {
}
