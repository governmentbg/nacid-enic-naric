package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseSpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept.RudiAcceptViewDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UdirecAcceptViewDataDTO extends RudiAcceptViewDataBaseDTO {
    private List<TrainingCourseSpecialityDTO> trainingCourseSpecialities;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;

}
