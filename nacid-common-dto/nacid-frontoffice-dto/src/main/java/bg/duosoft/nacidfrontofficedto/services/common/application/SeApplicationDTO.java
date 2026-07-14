package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.SeApplicantDetailsDTO;
import lombok.Data;

@Data
public abstract class SeApplicationDTO extends CommonApplicationDTO {
    private SeApplicantDetailsDTO applicantDetails;
}
