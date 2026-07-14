package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 05.04.2023
 * Time: 12:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeAppStatusDetailDTO {
    private Integer id;
    private ApplicationTypeDTO applicationType;
    private ReferenceDataDTO status;
}
