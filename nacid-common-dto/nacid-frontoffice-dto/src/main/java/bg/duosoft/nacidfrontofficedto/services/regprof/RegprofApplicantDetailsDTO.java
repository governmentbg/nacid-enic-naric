package bg.duosoft.nacidfrontofficedto.services.regprof;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 11:49
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegprofApplicantDetailsDTO extends CommonApplicantDetailsDTO {

    private boolean qualificationNamesDifferent;
    private QualificationDocumentNamesDTO qualificationNames;

}
