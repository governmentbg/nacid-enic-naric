package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.DuplicateApplicantDetailsDTO;
import lombok.Data;

@Data
public class DuplicateBaseApplicationDTO extends CommonApplicationDTO {
    private DuplicateApplicantDetailsDTO applicantDetails;
}
