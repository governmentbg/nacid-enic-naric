package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseSpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept.RudiAcceptViewDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SarAcceptViewDataDTO extends RudiAcceptViewDataBaseDTO {
    private PersonDTO diplomaOwner;
    private List<TrainingCourseSpecialityDTO> trainingCourseSpecialities;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
}
