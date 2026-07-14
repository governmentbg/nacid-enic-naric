package bg.duosoft.nacidfrontofficedto.services.docdegrees;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.common.application.RudiApplicationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.07.2022
 * Time: 11:21
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocDegreesApplicationDTO extends RudiApplicationDTO {

    private DocEducationDetailsDTO educationDetails;
}
