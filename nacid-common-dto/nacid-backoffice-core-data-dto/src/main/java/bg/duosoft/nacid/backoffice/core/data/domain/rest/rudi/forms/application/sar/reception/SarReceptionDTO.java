package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseSpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.reception.RudiBaseReceptionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.SarFlagDTO;
import lombok.Data;

import java.util.List;

@Data
public class SarReceptionDTO extends RudiBaseReceptionDTO {

    private Integer diplomaOwnerId;
    private SarFlagDTO sarFlag;
    private List<TrainingCourseSpecialityDTO> trainingCourseSpecialities;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
}
