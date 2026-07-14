package bg.duosoft.nacidfrontofficedto.services.signal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 15:23
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignalDetailsDTO {

    private String violationDescription;
    private String violationPlace;
    private String checkRequirement;
    private String damagesDescription;
    private String measuresTaken;
}
