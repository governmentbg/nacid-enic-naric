package bg.duosoft.nacidfrontofficedto.services.signal;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 15:22
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignalApplicationDTO extends CommonApplicationDTO {

    private SignalDetailsDTO signalDetails;
}
