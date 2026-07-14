package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingCourseSpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.reception.RudiBaseReceptionDTO;
import lombok.Data;

import java.util.List;

@Data
public class UdirecReceptionDTO extends RudiBaseReceptionDTO {

    private String personalDocumentTypeId;
    private List<TrainingCourseSpecialityDTO> trainingCourseSpecialities;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;

}
