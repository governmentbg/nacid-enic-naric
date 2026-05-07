package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RPTrainingCourseDTO {
    private Integer id;
    private ReferenceDataDTO educationType;
    private HigherTrainingCourseDTO higherTrainingCourse;
    private PostgraduateTrainingCourseDTO postgraduateTrainingCourse;
    private SecondaryTrainingCourseDTO secondaryTrainingCourse;
    private List<RPTrainingCourseSpecialityDTO> trainingCourseSpecialities;
    private TrainingCourseProfInstitutionExaminationDTO profInstitutionExamination;
    private TrainingCourseDocumentExaminationDTO documentExamination;
    private TrainingCourseQualificationExaminationDTO qualificationExamination;
}
