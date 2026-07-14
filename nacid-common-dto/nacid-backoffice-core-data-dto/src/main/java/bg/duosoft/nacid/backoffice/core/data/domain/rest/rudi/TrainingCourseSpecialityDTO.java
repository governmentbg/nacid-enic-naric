package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainingCourseSpecialityDTO {
    private Integer id;
    private String speciality;
    private String originalSpeciality;

}
