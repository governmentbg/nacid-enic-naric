package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfInstitutionFormerNameDTO {
    private Integer id;
    private Integer profInstitutionId;
    private String formerName;
    public ProfInstitutionFormerNameDTO(Integer id) {
        this.id = id;
    }
}
