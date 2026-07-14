package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.document_delivery.process_data;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.doc_delivery.DocumentDeliveryDetailDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DocumentDeliveryProcessDataDTO {
    private Integer applicationId;
    private List<DocumentDeliveryDetailDTO> details;
}
