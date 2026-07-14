package bg.duosoft.nacidcoreclient.client.fileStore;

import bg.duosoft.nacidcoreclient.config.ClientTokenFeignConfig;
import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.11.2022
 * Time: 16:57
 */
@FeignClient(name = "AdminFileStoreClient", url = "${feign.core-api.base-url}/v1/file-store", configuration = ClientTokenFeignConfig.class)
public interface AdminFileStoreClient extends BaseFileStoreClient{

    @PostMapping("/move-file")
    FileStoreEntryDTO moveFile(@RequestParam(name = "rootDirectoryNew") String rootDirectoryNew,
                               @RequestParam(name = "relativePathNew") String relativePathNew,
                               @RequestParam(name = "removeOriginal") Boolean removeOriginal,
                               @RequestBody FileStoreEntryDTO fileStoreEntry);

    @DeleteMapping
    void removeFile(@RequestParam(name = "rootDirectory") String rootDirectory,
                    @RequestParam(name = "relativePath") String relativePath,
                    @RequestParam(name = "fileId") String fileId);

    @GetMapping("/file-exists")
    boolean getFileExists(@RequestParam(name = "rootDirectory") String rootDirectory,
                                 @RequestParam(name = "relativePath") String relativePath,
                                 @RequestParam(name = "fileId") String fileId);

}
