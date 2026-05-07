package bg.duosoft.nacidcoreapi.service.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.CfgRecognitionCategoryDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 13:13
 */
public interface CfgRecognitionCategoryService {

    List<CfgRecognitionCategoryDTO> getRecognitionCategoryConfigs();
}
