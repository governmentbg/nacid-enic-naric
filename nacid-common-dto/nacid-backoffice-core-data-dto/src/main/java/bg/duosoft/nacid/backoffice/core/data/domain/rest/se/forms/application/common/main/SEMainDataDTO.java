package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNotesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveOptionFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GradingScaleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.common.main.RegprofMandatoryMainData;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SEMainDataDTO implements SEMandatoryMainData {
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
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
    private DocumentReceiveOptionFormDTO originalDocumentReceiveOption;
    private CountryDTO schoolCountry;
    private GradingScaleDTO gradingScale;

    //Main section specific data
    private String responsibleUser;
    private String userCreatedFullName;
    private Boolean personalDataUsageFlag;
    private Boolean dataAuthenticFlag;
    private Boolean officialEmailCommunicationFlag;
    private List<ApplicationNotesDTO> applicationNotes;
    private Boolean originalDocumentWaitingFlag;
}
