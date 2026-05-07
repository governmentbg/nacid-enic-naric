package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.AdditionalDocApplicantDetailsDTO;
import lombok.Data;

@Data
public class AdditionalDocBaseApplicationDTO extends CommonApplicationDTO {
    private AdditionalDocApplicantDetailsDTO applicantDetails;
}
