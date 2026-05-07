package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachedDocAttachmentDTO {
    private Integer id;
    private AttachmentDTO attachment;
    private ReferenceDataDTO attachmentVisibility;
}
