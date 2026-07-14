package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainingLocationSimpleDTO {
    private Integer id;
    private CountryDTO country;
    private String city;
    private Boolean isNotUniInstitution;
    private Integer examinationTrainingInstitutionId;
}
