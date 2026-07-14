package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.RudiApplicantDetailsDTO;
import bg.duosoft.nacidfrontofficedto.services.common.education.RudiEducationDetailsDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.07.2022
 * Time: 15:09
 */
@Data
public abstract class RudiApplicationDTO extends CommonApplicationDTO {

    private RudiApplicantDetailsDTO applicantDetails;
    private RudiEducationDetailsDTO educationDetails;
}
