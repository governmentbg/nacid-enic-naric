package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GradingScaleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SETrainingCourseDTO implements Serializable {
    private Integer id;
    private String schoolName;
    private String schoolSettlement;
    private CountryDTO schoolCountry;
    private CountryDTO schoolGradingScaleCountry;
    private String schoolGradingScaleSettlement;
    private String schoolGradingScaleName;
    private ReferenceDataDTO internationalGradingSystem;
    private String diplomaNumber;
    private Integer diplomaYear;
    private LocalDate diplomaDate;
    private String diplomaRegisterURL;
    private String diplomaApostilleURL;
    private String professionBgTitle;
    private String professionNativeTitle;
    private GradingScaleDTO gradingScale;
    private SEGradingScaleFormulaDTO gradingScaleFormula;
    private String additionalInfo;
    private List<SETrainingCourseStudiedSubjectDTO> studiedSubjects;
    private String additionalStudiedSubjectsNote;
    private List<SETrainingCourseAdditionalStudiedSubjectDTO> additionalStudiedSubjects;

    private String prevCertNumber;
    private String prevCertNote;
    private String additionalStudiedSubjectsSchoolData;
}
