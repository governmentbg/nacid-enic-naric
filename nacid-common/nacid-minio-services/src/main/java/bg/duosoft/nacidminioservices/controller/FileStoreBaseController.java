package bg.duosoft.nacidminioservices.controller;

import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidminioservices.service.FileStoreService;
import bg.duosoft.nacidminioservices.utils.FileUtils;
import bg.duosoft.nacidshareddata.exception.ForbiddenException;
import bg.duosoft.nacidshareddata.util.MimeTypeUtils;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.06.2022
 * Time: 11:13
 */
@Slf4j
public abstract class FileStoreBaseController {
    public abstract FileStoreService getFileStoreBaseService();
    protected void checkPermissions(String role) {
        if (Objects.nonNull(role) && !SecurityUtils.hasRole(role)) {
            throw new ForbiddenException();
        }
    }
    protected String getEditRole() {
        return null;
    }

    protected String getDeleteRole() {
        return null;
    }
    @PostMapping("/upload-restricted")
    public FileStoreEntryBaseDTO uploadRestricted(@RequestParam(name = "fileGroup", required = false, defaultValue = FileConstants.FILE_GROUP_GENERAL) String fileGroup,
                                                  @RequestParam(name = "pointer", required = false, defaultValue = FileConstants.DEFAULT_FILE_POINTER) String pointer,
                                                  @RequestParam(name = "rootDirectory", required = false, defaultValue = FileConstants.TEMP_ROOT_DIRECTORY) String rootDirectory,
                                                  @RequestParam(name = "relativePath") String relativePath,
                                                  @RequestParam("file") MultipartFile uploadedFile) {
        checkPermissions(getEditRole());
        try {
            FileStoreEntryBaseDTO entry = FileUtils.createFileStoreEntry(uploadedFile, rootDirectory, relativePath);
            return getFileStoreBaseService().saveNewFile(fileGroup, pointer, entry);
        } catch (IOException e) {
            log.error("File upload failed", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/save-new-restricted")
    public FileStoreEntryBaseDTO saveNewFileRestricted(@RequestParam(name = "fileGroup", required = false, defaultValue = FileConstants.FILE_GROUP_GENERAL) String fileGroup,
                                                   @RequestParam(name = "pointer", required = false, defaultValue = FileConstants.DEFAULT_FILE_POINTER) String pointer,
                                                   @RequestBody FileStoreEntryBaseDTO fileStoreEntry) {
        checkPermissions(getEditRole());
        return getFileStoreBaseService().saveNewFile(fileGroup, pointer, fileStoreEntry);
    }

    @GetMapping("/file-details")
    public FileStoreEntryBaseDTO getFileDetails(@RequestParam(name = "rootDirectory") String rootDirectory,
                                            @RequestParam(name = "relativePath") String relativePath,
                                            @RequestParam(name = "fileId") String fileId) {
        return getFileStoreBaseService().getFileStoreEntryDetailsOnly(rootDirectory, relativePath, fileId);
    }

    @GetMapping("/file-exists")
    public boolean getFileExists(@RequestParam(name = "rootDirectory") String rootDirectory,
                                                @RequestParam(name = "relativePath") String relativePath,
                                                @RequestParam(name = "fileId") String fileId) {
        return getFileStoreBaseService().fileExists(rootDirectory, relativePath, fileId);
    }

    @GetMapping("/file-details-content")
    public FileStoreEntryBaseDTO getFileDetailsAndContent(@RequestParam(name = "rootDirectory") String rootDirectory,
                                                      @RequestParam(name = "relativePath") String relativePath,
                                                      @RequestParam(name = "fileId") String fileId) {
        return getFileStoreBaseService().getFileStoreEntryDetailsAndContent(rootDirectory, relativePath, fileId);
    }

    @GetMapping("/file-content")
    public ResponseEntity<byte[]> getFileContent(@RequestParam(name = "rootDirectory") String rootDirectory,
                                                 @RequestParam(name = "relativePath") String relativePath,
                                                 @RequestParam(name = "fileId") String fileId,
                                                 @RequestParam(defaultValue = "attachment", required = false) String disposition) {
        FileStoreEntryBaseDTO entry = getFileStoreBaseService().getFileStoreEntryDetailsAndContent(rootDirectory, relativePath, fileId);
        if (entry != null || entry.getContent() == null) {
            MediaType mediaType = getMediaType(entry.getContentType(), entry.getContent(), entry.getFileName());
            String fileNameEncoded = URLEncoder.encode(entry.getFileName(), StandardCharsets.UTF_8);
            return ResponseEntity.ok()
                    .header("Content-Disposition", String.format("%s;filename*=UTF-8''%s;filename=\"%s\"", disposition, fileNameEncoded, fileNameEncoded))
                    .contentType(mediaType).body(entry.getContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/move-file")
    public FileStoreEntryBaseDTO moveFile(@RequestParam(name = "rootDirectoryNew") String rootDirectoryNew,
                                      @RequestParam(name = "relativePathNew") String relativePathNew,
                                      @RequestParam(name = "removeOriginal") Boolean removeOriginal,
                                      @RequestBody FileStoreEntryBaseDTO fileStoreEntry) {
        checkPermissions(getEditRole());
        return getFileStoreBaseService().moveFile(rootDirectoryNew, relativePathNew, fileStoreEntry, removeOriginal);
    }

    @DeleteMapping
    public void removeFile(@RequestParam(name = "rootDirectory") String rootDirectory,
                           @RequestParam(name = "relativePath") String relativePath,
                           @RequestParam(name = "fileId") String fileId) {
        checkPermissions(getDeleteRole());
        getFileStoreBaseService().removeFile(rootDirectory, relativePath, fileId);
    }


    private MediaType getMediaType(String contentType, byte[] responseContent, String fileName) {
        MediaType mediaType = null;
        if (contentType != null && StringUtils.hasText(contentType)) {
            try {
                mediaType = MediaType.parseMediaType(contentType);
            } catch (InvalidMediaTypeException e) {
                //Do nothing
            }
        }
        if (mediaType == null) {
            try {
                mediaType = MediaType.parseMediaType(MimeTypeUtils.guessMimeFromBytes(responseContent, fileName));
            } catch (Exception e) {
                log.warn("Could not determine media type");
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
            }
        }
        return mediaType;
    }

}
