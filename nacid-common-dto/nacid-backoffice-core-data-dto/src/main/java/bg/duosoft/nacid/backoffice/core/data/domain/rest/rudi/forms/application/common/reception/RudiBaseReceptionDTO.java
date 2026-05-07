package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education.mandatory.RudiMandatoryEduData;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.mandatory.RudiMandatoryMainData;
import lombok.Data;

@Data
public class RudiBaseReceptionDTO implements RudiMandatoryMainData, RudiMandatoryEduData {

    //Main Data
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
    private String serviceTypeId;
    private Boolean personalDataUsageFlag;
    private Boolean dataAuthenticFlag;

    //Education Data
    private Integer baseUniversityId;
    private String manualTempUniName;

}
