package bg.duosoft.nacid.backoffice.core.client.client.fileStore;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.FileStoreEntryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:53
 */
public interface BoBaseFileStoreClient {

    @PostMapping("/save-new")
    FileStoreEntryDTO saveNewFile(@RequestParam(name = "fileGroup", required = false) String fileGroup,
                                  @RequestParam(name = "pointer", required = false) String pointer,
                                  @RequestBody FileStoreEntryDTO storeRequest);

    @PostMapping("/save-new-restricted")
    FileStoreEntryDTO saveNewFileRestricted(@RequestParam(name = "fileGroup", required = false) String fileGroup,
                                  @RequestParam(name = "pointer", required = false) String pointer,
                                  @RequestBody FileStoreEntryDTO fileStoreEntry);

    @GetMapping("/file-details")
    FileStoreEntryDTO getFileDetails(@RequestParam(name = "rootDirectory") String rootDirectory,
                                     @RequestParam(name = "relativePath") String relativePath,
                                     @RequestParam(name = "fileId") String fileId);

    @GetMapping("/file-details-content")
    FileStoreEntryDTO getFileDetailsAndContent(@RequestParam(name = "rootDirectory") String rootDirectory,
                                               @RequestParam(name = "relativePath") String relativePath,
                                               @RequestParam(name = "fileId") String fileId);

    @GetMapping("/file-content-restricted")
    ResponseEntity<byte[]> getFileContent(@RequestParam(name = "rootDirectory") String rootDirectory,
                                          @RequestParam(name = "relativePath") String relativePath,
                                          @RequestParam(name = "fileId") String fileId,
                                          @RequestParam(defaultValue = "attachment", required = false) String disposition);
}
