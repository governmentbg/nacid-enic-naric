package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.util.minio.MinioBucketManager;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidminioservices.controller.FileStoreBaseController;
import bg.duosoft.nacidminioservices.service.FileStoreService;
import bg.duosoft.nacidminioservices.utils.FileUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
@Api(tags = Tags.FILE_STORE)
@RequestMapping("/api/v1/file-store")
@RequiredArgsConstructor
public class FileStoreController extends FileStoreBaseController {
    private final FileStoreService fileStoreService;
    private final ApplicationsService applicationsService;

    @Override
    public FileStoreService getFileStoreBaseService() {
        return fileStoreService;
    }

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getDeleteRole() {
        return null;
    }

    @PostMapping("/upload-app-file")
    public FileStoreEntryBaseDTO uploadApplicationFile(
            @RequestParam(name = "fileGroup", required = false, defaultValue = FileConstants.FILE_GROUP_GENERAL) String fileGroup,
            @RequestParam(name = "pointer", required = false, defaultValue = FileConstants.DEFAULT_FILE_POINTER) String pointer,
            @RequestParam(name = "applicationId") Integer applicationId,
            @RequestParam("file") MultipartFile uploadedFile) {
        try {
            ApplicationDTO application = applicationsService.getApplicationById(applicationId);
            if (Objects.isNull(application)) {
                throw new RuntimeException("[UploadApplicationFile] Cannot find application ! ApplicationId: " + applicationId);
            }

            FileStoreEntryBaseDTO entry = FileUtils.createFileStoreEntry(uploadedFile, MinioBucketManager.selectBucketName(application), MinioBucketManager.buildAppsRelativePath(application));
            return fileStoreService.saveNewFile(fileGroup, pointer, entry);
        } catch (IOException e) {
            log.error("File upload failed", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/upload")
    public FileStoreEntryBaseDTO upload(
            @RequestParam(name = "fileGroup", required = false, defaultValue = FileConstants.FILE_GROUP_GENERAL) String fileGroup,
            @RequestParam(name = "pointer", required = false, defaultValue = FileConstants.DEFAULT_FILE_POINTER) String pointer,
            @RequestParam(name = "rootDirectory", required = false, defaultValue = FileConstants.TEMP_ROOT_DIRECTORY) String rootDirectory,
            @RequestParam(name = "relativePath") String relativePath,
            @RequestParam("file") MultipartFile uploadedFile) {
        try {
            FileStoreEntryBaseDTO entry = FileUtils.createFileStoreEntry(uploadedFile, rootDirectory, relativePath);
            return fileStoreService.saveNewFile(fileGroup, pointer, entry);
        } catch (IOException e) {
            log.error("File upload failed", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/save-new")
    public FileStoreEntryBaseDTO saveNewFile(@RequestParam(name = "fileGroup", required = false, defaultValue = FileConstants.FILE_GROUP_GENERAL) String fileGroup,
                                             @RequestParam(name = "pointer", required = false, defaultValue = FileConstants.DEFAULT_FILE_POINTER) String pointer,
                                             @RequestBody FileStoreEntryBaseDTO storeRequest) {
        return fileStoreService.saveNewFile(fileGroup, pointer, storeRequest);
    }

    @PostMapping("/copy-file")
    public void copyFile(
            @RequestParam(name = "rootDirectoryNew") String rootDirectoryNew,
            @RequestParam(name = "relativePathNew") String relativePathNew,
            @RequestParam(name = "rootDirectoryOld") String rootDirectoryOld,
            @RequestParam(name = "relativePathOld") String relativePathOld,
            @RequestParam(name = "fileId") String fileId) {
        fileStoreService.copyFile(rootDirectoryNew, relativePathNew, rootDirectoryOld, relativePathOld, fileId);
    }
}
