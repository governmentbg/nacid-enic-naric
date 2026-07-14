package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationRecognitionPurposeDTO {
    private Integer id;
    private Integer applicationId;
    private ReferenceDataDTO recognitionPurpose;
    private String notes;

}
