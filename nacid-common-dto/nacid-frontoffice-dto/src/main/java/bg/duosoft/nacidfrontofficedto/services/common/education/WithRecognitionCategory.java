package bg.duosoft.nacidfrontofficedto.services.common.education;

import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.05.2023
 * Time: 12:13
 */
public interface WithRecognitionCategory {

    ReferenceDataDTO getRecognitionCategory();
    void setRecognitionCategory(ReferenceDataDTO recognitionCategory);
}
