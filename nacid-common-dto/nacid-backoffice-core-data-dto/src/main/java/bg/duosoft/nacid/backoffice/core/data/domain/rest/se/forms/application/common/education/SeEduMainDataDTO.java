package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GradingScaleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.se.SEGradingScaleFormulaDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SeEduMainDataDTO implements SeMandatoryEduData {
    private CountryDTO schoolCountry;

    private String schoolSettlement;

    private String schoolName;

    private String diplomaNumber;

    private Integer diplomaYear;

    private GradingScaleDTO gradingScale;

    private SEGradingScaleFormulaDTO gradingScaleFormula;

    private List<String> recognitionPurposes;

    private String recognitionPurposeOtherDetails;

    private LocalDate diplomaDate;

    private String diplomaRegisterURL;

    private String diplomaApostilleURL;

    private String professionBgTitle;

    private String professionNativeTitle;

    private String additionalInfo;
}
