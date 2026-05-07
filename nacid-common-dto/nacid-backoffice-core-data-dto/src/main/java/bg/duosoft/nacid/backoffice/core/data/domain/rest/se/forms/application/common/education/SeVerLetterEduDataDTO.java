package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeVerLetterEduDataDTO extends SeEduMainDataDTO {
    private Boolean isForeignGradingScale;
    private CountryDTO schoolGradingScaleCountry;
    private String schoolGradingScaleSettlement;
    private String schoolGradingScaleName;
    private String prevCertNumber;
    private String prevCertNote;
}
