package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ProfGroupDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRecognizedDetailsDTO {
    private Integer applicationId;
    private String recognizedEduLevel;
    private String recognizedQualification;
    private ProfGroupDTO profGroup;
}
