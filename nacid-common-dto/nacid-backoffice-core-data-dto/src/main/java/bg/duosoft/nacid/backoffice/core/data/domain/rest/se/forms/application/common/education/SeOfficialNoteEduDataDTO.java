package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.se.SETrainingCourseAdditionalStudiedSubjectDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeOfficialNoteEduDataDTO extends SeEduMainDataDTO {
    private Boolean isForeignGradingScale;
    private CountryDTO schoolGradingScaleCountry;
    private String schoolGradingScaleSettlement;
    private String schoolGradingScaleName;
    private String additionalStudiedSubjectsNote;
    private List<SETrainingCourseAdditionalStudiedSubjectDTO> additionalStudiedSubjects;
    private String additionalStudiedSubjectsSchoolData;
    private String prevCertNumber;
    private String prevCertNote;
}
