package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RPTrainingExperienceDTO {
    private Integer id;
    private Boolean notRestrictedFlag;
    private Boolean regulatedEducationTrainingFlag;
    private RPTrainingCourseDTO trainingCourse;
    private RPProfessionExperienceDTO professionExperience;
}
