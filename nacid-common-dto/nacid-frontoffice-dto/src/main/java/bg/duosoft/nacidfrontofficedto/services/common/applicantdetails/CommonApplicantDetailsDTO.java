package bg.duosoft.nacidfrontofficedto.services.common.applicantdetails;

import bg.duosoft.nacidfrontofficedto.address.ContactAddressDTO;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationDocumentReceiveMethodDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 30.08.2022
 * Time: 13:29
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonApplicantDetailsDTO {

    private Integer applicationId;
    private ServicesApplicantDTO applicant;
    private boolean applicantHasRepresentative;
    private NaturalPersonDTO representative;
    private String representativeCapacity;
    private String representativeCompanyIdentifier;
    private Boolean hasContactAddress;
    private ContactAddressDTO contactAddress;
    private Boolean agreeDataUsage;
    private Boolean documentsDeclaration;
    private Boolean agreeMailCorrespondence;
    private String applicantTitleBefore;
    private String applicantTitleAfter;
    private List<String> certificateReceiveForms;
    private ApplicationDocumentReceiveMethodDTO resultReceiveElectronic;
    private ApplicationDocumentReceiveMethodDTO resultReceivePaper;
}
