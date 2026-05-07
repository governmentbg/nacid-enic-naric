package bg.duosoft.nacidfrontofficedto.services.common.applicantdetails;

import bg.duosoft.nacidfrontofficedto.person.NaturalPersonNamesDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.05.2022
 * Time: 17:26
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RudiApplicantDetailsDTO extends CommonApplicantDetailsDTO {

    private boolean diplomaNamesDifferent;
    private NaturalPersonNamesDTO diplomaNames;
}
