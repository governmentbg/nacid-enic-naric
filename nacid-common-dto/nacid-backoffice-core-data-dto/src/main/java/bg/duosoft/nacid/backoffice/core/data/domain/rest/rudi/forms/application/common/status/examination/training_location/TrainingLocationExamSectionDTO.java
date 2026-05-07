package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.training_location;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.TrainingLocationSimpleDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.UniversitySimpleDTO;
import lombok.Data;

import java.util.List;

@Data
public class TrainingLocationExamSectionDTO {
    private Integer applicationId;
    private List<Integer> universityIds;
    private List<UniversitySimpleDTO> universities;
    private Boolean isLegitimate;
    private Boolean isStatusUpdated;
    private List<TrainingLocationSimpleDTO> trainingLocations;
}
