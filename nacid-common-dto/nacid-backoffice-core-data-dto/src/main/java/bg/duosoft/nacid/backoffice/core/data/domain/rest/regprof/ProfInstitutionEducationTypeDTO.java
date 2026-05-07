package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfInstitutionEducationTypeDTO {
    private Integer id;
    private Integer profInstitutionId;
    private ReferenceDataDTO educationType;
    public ProfInstitutionEducationTypeDTO(Integer id) {
        this.id = id;
    }
}
