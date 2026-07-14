package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.common.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNotesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class RegprofMainDataDTO implements RegprofMandatoryMainData {
    private Integer applicationId;
    private Integer applicantId;
    private String personalDocumentTypeId;
    private Integer representativeId;
    private Integer representativeCompanyId;
    private Boolean representativeCompanyFlag;
    private String representativeCapacity;
    private Boolean diffDiplomaNamesFlag;
    private ApplicantDiplomaNamesDTO applicantDiplomaNames;
    private Integer contactAddressId;
    private String serviceTypeId;
    private Boolean apostilleApplicationFlag;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
    //Main section specific data
    private String responsibleUser;
    private String userCreatedFullName;
    private Boolean personalDataUsageFlag;
    private Boolean dataAuthenticFlag;
    private List<ApplicationNotesDTO> applicationNotes;
    private LocalDate endDate;

}
