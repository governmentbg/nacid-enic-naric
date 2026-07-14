package bg.duosoft.nacidcoreapi.service.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import bg.duosoft.nacidfrontofficedto.nomenclature.GraduationDocTypeDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.GraduationDocTypeFilterDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 13:31
 */
public interface GraduationDocTypeService extends NomenclatureServiceBase<Integer, GraduationDocTypeDTO, GraduationDocTypeFilterDTO> {

    List<GraduationDocTypeDTO> getByEducationType(EducationType educationType, boolean onlyActive);
}
