package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.doc_delivery;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.LibservAppDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.LibservObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDeliveryAppDTO implements Serializable, LibservObject {

    private LibservAppDTO libservApp;
    private List<DocumentDeliveryDetailDTO> details;

    public DocumentDeliveryAppDTO(LibservAppDTO libservApp) {
        this.libservApp = libservApp;
    }

}
