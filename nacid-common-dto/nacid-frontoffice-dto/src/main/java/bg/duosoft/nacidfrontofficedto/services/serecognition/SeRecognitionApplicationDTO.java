package bg.duosoft.nacidfrontofficedto.services.serecognition;

import bg.duosoft.nacidfrontofficedto.services.common.application.SeApplicationDTO;
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
public class SeRecognitionApplicationDTO extends SeApplicationDTO {
    private SeEducationDetailsDTO educationDetails;
}
