package bg.duosoft.nacidfrontofficedto.nomenclature;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.01.2023
 * Time: 11:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CfgServiceTypeDTO {

    private String id;
    private ReferenceDataDTO serviceType;
    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
    private Integer executionDays;
    private ReferenceDataDTO executionDaysType;
}
