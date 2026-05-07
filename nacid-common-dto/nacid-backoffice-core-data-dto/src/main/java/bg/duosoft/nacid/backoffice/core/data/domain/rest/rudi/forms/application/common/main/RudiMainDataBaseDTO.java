package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNotesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.mandatory.RudiMandatoryMainData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RudiMainDataBaseDTO implements RudiMandatoryMainData {

    private Integer applicationId;
    private Integer applicantId;
    private Integer representativeId;
    private Integer representativeCompanyId;
    private Boolean representativeCompanyFlag;
    private Boolean representativeAuthorizedFlag;
    private String representativeCapacity;
    private Boolean isBgAddressPartOfRepresentative;
    private Boolean diffDiplomaNamesFlag;
    private ApplicantDiplomaNamesDTO applicantDiplomaNames;
    private Integer contactAddressId;
    private Boolean officialEmailCommunicationFlag;
    private Boolean personalDataUsageFlag;
    private Boolean dataAuthenticFlag;
    private List<ApplicationNotesDTO> applicationNotes;
    private String serviceTypeId;
    private String responsibleUser;
    private String userCreatedFullName;
}
