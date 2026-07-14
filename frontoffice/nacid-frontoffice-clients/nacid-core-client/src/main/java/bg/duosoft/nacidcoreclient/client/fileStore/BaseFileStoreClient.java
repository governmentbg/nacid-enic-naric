package bg.duosoft.nacidcoreclient.client.fileStore;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryCreationRequestDTO;
import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:53
 */
public interface BaseFileStoreClient {

    @PostMapping("/save-new")
    FileStoreEntryDTO saveNewFile(@RequestParam(name = "fileGroup", required = false) String fileGroup,
                                  @RequestParam(name = "pointer", required = false) String pointer,
                                  @RequestBody FileStoreEntryCreationRequestDTO creationRequest);

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

    @GetMapping("/file-content")
    ResponseEntity<byte[]> getFileContent(@RequestParam(name = "rootDirectory") String rootDirectory,
                                          @RequestParam(name = "relativePath") String relativePath,
                                          @RequestParam(name = "fileId") String fileId,
                                          @RequestParam(defaultValue = "attachment", required = false) String disposition);
}
