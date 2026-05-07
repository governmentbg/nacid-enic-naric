package bg.duosoft.nacidfrontofficedto.services.unichecks;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 11:45
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UniChecksApplicationDTO extends CommonApplicationDTO {

    private UniChecksEducationDetailsDTO educationDetails;
}
