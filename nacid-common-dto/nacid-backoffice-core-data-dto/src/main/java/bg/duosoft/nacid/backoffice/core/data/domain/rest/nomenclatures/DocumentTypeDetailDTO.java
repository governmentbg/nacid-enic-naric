package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 18.08.2022
 * Time: 17:12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentTypeDetailDTO {
    private Integer id;
    private ApplicationTypeDTO applicationType;
    private ApplicationSubtypeDTO applicationSubtype;
    private String condition;
    private String additionalDescription;
    private String template;
    private ReferenceDataDTO documentCategory;
    private ReferenceDataDTO finalizationType;
    private ReferenceDataDTO defaultAttachmentVisibility;
}
