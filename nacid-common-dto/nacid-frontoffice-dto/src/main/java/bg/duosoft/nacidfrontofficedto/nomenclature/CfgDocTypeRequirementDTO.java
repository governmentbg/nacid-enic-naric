package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.01.2023
 * Time: 18:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CfgDocTypeRequirementDTO {

    private DocTypeDTO docType;
    private String copyTypeCode;
    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
    private String requirementKey;
    private String requirementExpression;
    private String templateUrl;
}
