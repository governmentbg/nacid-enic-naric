package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.education;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseSpecialityDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SarUdirecEduDataCommonDTO extends RudiEduDataBaseDTO {

    private List<TrainingCourseSpecialityDTO> trainingCourseSpecialities;

    private Boolean graduationWayThesis;
    private Boolean graduationWayExam;
    private Boolean graduationWayThesisAndExam;

    private String qualification;
    private String originalQualification;

    private StringIdDTO schoolCountry;
    private String schoolCity;
    private String schoolName;
    private String schoolGraduationDate;
    private String schoolNotes;

    private Boolean recognitionPurposeContinueEducation;
    private Boolean recognitionPurposeWork;
    private Boolean recognitionPurposeProjectWork;
    private Boolean recognitionPurposeOther;
    private String recognitionPurposeNotes;
}
