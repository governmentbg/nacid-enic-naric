package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondarySpecialityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RPTrainingCourseSpecialityDTO {
    private Integer id;
    private SecondarySpecialityDTO secondarySpeciality;
    private String higherSpeciality;
    private String sdkSpeciality;
}
