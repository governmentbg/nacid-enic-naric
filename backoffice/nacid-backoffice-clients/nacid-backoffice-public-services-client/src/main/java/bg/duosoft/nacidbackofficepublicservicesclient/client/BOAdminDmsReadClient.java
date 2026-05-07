package bg.duosoft.nacidbackofficepublicservicesclient.client;

import bg.duosoft.nacidbackofficepublicservicesclient.config.ClientTokenFeignConfig;
import bg.duosoft.nacidfrontofficedto.services.common.application.DmsDocDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 12:03
 */
@FeignClient(name = "BOAdminDmsReadClient", url = "${feign.backoffice-public-services.base-url}/v1/dms-read", configuration = ClientTokenFeignConfig.class)
public interface BOAdminDmsReadClient {

    @GetMapping("/dms-doc-for-attached-doc/{attachedDocId}")
    DmsDocDetailsDTO getDmsDocForAppAttachedDocId(@PathVariable Integer attachedDocId);

    @GetMapping("/dms-file-content/{docId}/{fileId}")
    ResponseEntity<byte[]> getDmsFileContent(@PathVariable Integer docId, @PathVariable Integer fileId);
}
