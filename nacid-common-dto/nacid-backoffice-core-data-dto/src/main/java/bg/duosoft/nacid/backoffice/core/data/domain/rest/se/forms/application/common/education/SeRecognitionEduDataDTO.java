package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.se.SETrainingCourseStudiedSubjectDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeRecognitionEduDataDTO extends SeEduMainDataDTO {
    private Boolean isForeignGradingScale;
    private CountryDTO schoolGradingScaleCountry;
    private String schoolGradingScaleSettlement;
    private String schoolGradingScaleName;
    private List<SETrainingCourseStudiedSubjectDTO> studiedSubjects;
}
