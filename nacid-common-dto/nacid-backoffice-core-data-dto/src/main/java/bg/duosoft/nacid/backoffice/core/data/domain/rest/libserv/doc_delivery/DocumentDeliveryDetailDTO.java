package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.doc_delivery;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDeliveryDetailDTO implements Serializable {

    private Integer id;
    private String bibliographicData;
    private Boolean isDigitalCatalogue;
    private Boolean isBgLibrary;
    private Boolean isForeignLibrary;
    private ReferenceDataDTO copyType;
    private DocumentTypeDTO documentType;
    private AttachmentDTO attachment;
    private String docflowId;

    private String abdocsViewDocumentUrl;
}
