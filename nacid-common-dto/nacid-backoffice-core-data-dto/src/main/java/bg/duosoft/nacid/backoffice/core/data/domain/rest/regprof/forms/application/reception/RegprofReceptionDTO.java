package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.common.main.RegprofMandatoryMainData;
import lombok.Data;

@Data
public class RegprofReceptionDTO implements RegprofMandatoryMainData {
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
    private String apostilleParentDocRegNumber;
    private Boolean hasEducation;
    private Boolean hasExperience;
    private CountryDTO applicationCountry;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
    private Boolean personalDataUsageFlag;
    private Boolean dataAuthenticFlag;
}
