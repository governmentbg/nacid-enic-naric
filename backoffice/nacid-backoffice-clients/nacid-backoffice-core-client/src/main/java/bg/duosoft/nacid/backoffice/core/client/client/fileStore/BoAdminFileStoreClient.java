package bg.duosoft.nacid.backoffice.core.client.client.fileStore;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.FileStoreEntryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.11.2022
 * Time: 16:57
 */
@FeignClient(name = "BoAdminFileStoreClient", url = "${feign.backoffice-core.base-url}/v1/file-store", configuration = ClientTokenFeignConfig.class)
public interface BoAdminFileStoreClient extends BoBaseFileStoreClient {

    @PostMapping("/move-file")
    FileStoreEntryDTO moveFile(@RequestParam(name = "rootDirectoryNew") String rootDirectoryNew,
                               @RequestParam(name = "relativePathNew") String relativePathNew,
                               @RequestParam(name = "removeOriginal") Boolean removeOriginal,
                               @RequestBody FileStoreEntryDTO fileStoreEntry);

    @PostMapping("/copy-file")
    FileStoreEntryDTO copyFile(@RequestParam(name = "rootDirectoryNew") String rootDirectoryNew,
                               @RequestParam(name = "relativePathNew") String relativePathNew,
                               @RequestParam(name = "rootDirectoryOld") String rootDirectoryOld,
                               @RequestParam(name = "relativePathOld") String relativePathOld,
                               @RequestParam(name = "fileId") String fileId);

    @DeleteMapping
    void removeFile(@RequestParam(name = "rootDirectory") String rootDirectory,
                    @RequestParam(name = "relativePath") String relativePath,
                    @RequestParam(name = "fileId") String fileId);

}
