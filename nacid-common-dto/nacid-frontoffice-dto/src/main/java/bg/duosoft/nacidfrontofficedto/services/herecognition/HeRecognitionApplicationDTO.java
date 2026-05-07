package bg.duosoft.nacidfrontofficedto.services.herecognition;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.common.application.RudiApplicationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.05.2022
 * Time: 11:00
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeRecognitionApplicationDTO extends RudiApplicationDTO {

    private HeEducationDetailsDTO educationDetails;
}
