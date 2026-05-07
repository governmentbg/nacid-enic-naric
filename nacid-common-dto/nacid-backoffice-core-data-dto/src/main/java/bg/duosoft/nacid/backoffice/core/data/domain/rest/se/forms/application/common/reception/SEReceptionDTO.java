package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicantDiplomaNamesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveOptionFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GradingScaleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.main.SEMandatoryMainData;
import lombok.Data;

import java.util.List;

@Data
public class SEReceptionDTO implements SEMandatoryMainData {
    private Integer applicantId;
    private Integer representativeId;
    private Integer representativeCompanyId;
    private Boolean representativeCompanyFlag;
    private String representativeCapacity;
    private Integer contactAddressId;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
    private DocumentReceiveOptionFormDTO originalDocumentReceiveOption;
    private String personalDocumentTypeId;
    private Boolean diffDiplomaNamesFlag;
    private ApplicantDiplomaNamesDTO applicantDiplomaNames;
    private List<String> kinds;
    private CountryDTO schoolCountry;
    private GradingScaleDTO gradingScale;
    private Boolean personalDataUsageFlag;
    private Boolean dataAuthenticFlag;
    private Boolean officialEmailCommunicationFlag;
    private Boolean originalDocumentWaitingFlag;
}
