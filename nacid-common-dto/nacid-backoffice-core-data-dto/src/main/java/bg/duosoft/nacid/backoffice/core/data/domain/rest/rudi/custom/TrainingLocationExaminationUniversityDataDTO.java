package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainingLocationExaminationUniversityDataDTO {
    private UniversitySimpleDTO university;
    private ReferenceDataDTO uniExamTrainingLocation;
}
