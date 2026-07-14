package bg.duosoft.nacidfrontofficedto.services.regprof;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import bg.duosoft.nacidfrontofficedto.services.common.application.CommonApplicationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 11:48
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegprofApplicationDTO extends CommonApplicationDTO {

    private RegprofApplicantDetailsDTO applicantDetails;
    private RegprofEducationDetailsDTO educationDetails;

    private Boolean apostilleApplication;
    private String externalSystemId;
    private String externalSystemDocumentId;
}
