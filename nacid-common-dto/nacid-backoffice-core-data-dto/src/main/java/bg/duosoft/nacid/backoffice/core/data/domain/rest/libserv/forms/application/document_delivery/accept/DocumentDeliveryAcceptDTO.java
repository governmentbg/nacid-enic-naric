package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.document_delivery.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.accept.LibservAcceptBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDeliveryAcceptDTO extends LibservAcceptBaseDTO {

    private DocumentDeliveryAcceptViewDataDTO viewData;

}
