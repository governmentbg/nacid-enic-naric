package bg.duosoft.nacid.backoffice.core.client.client.common.abdocs;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface AbdocsFileTransferBaseClient {

    @PostMapping
    AttachedDocDTO abdocsTransferAttachedDocFiles(@RequestParam("applicationId") Integer applicationId, @RequestParam("attachedDocId") Integer attachedDocId);

}
