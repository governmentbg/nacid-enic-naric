package bg.duosoft.nacid.backoffice.core.client.client.common.abdocs;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.DocumentRegistrationResultDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.DocumentRegistrationRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 14:25
 */
public interface AbdocsCoreBaseClient {

    @GetMapping(value = "/{docflowId}")
    Doc selectAbdocsDoc(@PathVariable Integer docflowId);

    @GetMapping(value = "/{docflowId}/file/{fileId}")
    ResponseEntity<byte[]> getAbdocsFileContent(@PathVariable Integer docflowId, @PathVariable Integer fileId);

    @PostMapping(value = "/registration")
    DocumentRegistrationResultDTO registerDocument(@RequestBody DocumentRegistrationRequestDTO registrationRequest);

    @DeleteMapping(value = "/{docId}")
    void deleteDocument(@PathVariable("docId") Integer docId);
}
