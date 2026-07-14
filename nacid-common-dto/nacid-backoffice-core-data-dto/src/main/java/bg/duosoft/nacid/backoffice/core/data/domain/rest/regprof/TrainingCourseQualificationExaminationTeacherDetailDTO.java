package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCourseQualificationExaminationTeacherDetailDTO {
    private Integer id;
    private String schoolGrade;
    private String schoolType;
    private String schoolAgeRange;
    private String schoolSubject;
}
