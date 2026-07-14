package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.12.2022
 * Time: 13:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CfgGraduationWayDTO {

    private ReferenceDataDTO graduationWay;
    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
}
