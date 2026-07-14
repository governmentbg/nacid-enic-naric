package bg.duosoft.nacidfrontofficedto.services.common.applicantdetails;

import bg.duosoft.nacidfrontofficedto.person.NaturalPersonNamesDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DuplicateApplicantDetailsDTO extends CommonApplicantDetailsDTO {
    private boolean diplomaNamesDifferent;
    private NaturalPersonNamesDTO diplomaNames;
}
