package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.examination.parts.qualification;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdNameDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.TrainingCourseQualificationExaminationTeacherDetailDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QualificationExamSectionDTO {
    private Integer applicationId;
    private ReferenceDataDTO educationType;
    private String qualification;
    private IntegerIdNameDTO secondaryQualification;
    private ReferenceDataDTO educationLevel;
    private List<String> qualificationDegreeNames;
    private ReferenceDataDTO recognizedEducationLevel;
    private ReferenceDataDTO recognizedQualificationDegree;
    private String recognizedProfession;
    private String recProfQualificationModules;
    private String profQualificationModules;
    private Integer articleItemId;
    private Integer articleDirectiveId;
    private Boolean isRecognizedQualificationTeacher;
    private List<TrainingCourseQualificationExaminationTeacherDetailDTO> teacherDetails;
    private Boolean hasArticleDirectiveWarning;
}
