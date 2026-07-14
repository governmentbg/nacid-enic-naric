package bg.duosoft.nacidcoreapi.service.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidfrontofficedto.nomenclature.NationalUniversityDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.NationalUniversityDataFilterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 13:34
 */
public interface NationalUniversityService extends NomenclatureServiceBase<String, NationalUniversityDTO, NationalUniversityDataFilterDTO> {

    void updateAllToInactive();
}
