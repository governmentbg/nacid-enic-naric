package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 13:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CfgRecognitionCategoryDTO {

    private ReferenceDataDTO recognitionCategory;
    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
}
