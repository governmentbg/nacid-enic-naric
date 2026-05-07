package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentTypeDetailsFilterDTO {
    private Integer docType;
    private String docCategory;
    private Integer applicationId;
    private String applicationType;
    private String applicationSubType;
}
