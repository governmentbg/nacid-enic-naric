package bg.duosoft.nacidcoreapi.repository.nomenclatures.custom;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureSearchBaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GraduationDocTypeEntity;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.GraduationDocTypeFilterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 18:34
 */
public interface GraduationDocTypeSearchRepository extends NomenclatureSearchBaseRepository<Integer, GraduationDocTypeEntity, GraduationDocTypeFilterDTO> {
}
