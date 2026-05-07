package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.04.2023
 * Time: 16:09
 */
@Data
public class CfgDocTypeDTO {

    private Integer id;
    private DocTypeDTO docType;
    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
    private String showExpression;
}
