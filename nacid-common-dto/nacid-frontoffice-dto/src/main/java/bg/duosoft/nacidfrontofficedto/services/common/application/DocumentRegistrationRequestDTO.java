package bg.duosoft.nacidfrontofficedto.services.common.application;

import bg.duosoft.nacidfrontofficedto.services.common.applicantdetails.CommonApplicantDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRegistrationRequestDTO {
    private ApplicationType applicationType;
    private ApplicationSubtype applicationSubtype;
    private CommonApplicantDetailsDTO applicantDetails;
    private Boolean isApostille;
    private String externalSystemParentDocId;
    private String externalSystemParentRegNumber;
    private String subtypeKind;
    private Boolean biblioForeignSearch;
    private Boolean biblioNacidSearch;
}
