package bg.duosoft.nacidcoreapi.controller.v1.common;

import bg.duosoft.nacidcoreapi.integration.captcha.service.GoogleCaptchaService;
import bg.duosoft.nacidcoreapi.service.nomenclature.DocTypeService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidcoredata.util.security.SecurityRole;
import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryCreationRequestDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocTypeDTO;
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
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.06.2022
 * Time: 11:13
 */
@Slf4j
@RestController
@Api(tags = Tags.FILE_STORE)
@RequestMapping("/api/v1/file-store")
@RequiredArgsConstructor
public class FileStoreController extends FileStoreBaseController {
    private final FileStoreService fileStoreService;
    private final GoogleCaptchaService googleCaptchaService;
    private final DocTypeService docTypeService;

    @Override
    public FileStoreService getFileStoreBaseService() {
        return fileStoreService;
    }
    @Override
    public String getEditRole() {
        return SecurityRole.FILE_STORE_ENTRY_EDIT;
    }

    @Override
    public String getDeleteRole() {
        return SecurityRole.FILE_STORE_ENTRY_DELETE;
    }

    @PostMapping("/upload")
    public FileStoreEntryBaseDTO upload(HttpServletRequest request,
                                    @RequestPart(required = false) String captchaToken,
                                    @RequestParam(name = "docTypeId", required = false) Integer docTypeId,
                                    @RequestParam(name = "fileGroup", required = false, defaultValue = FileConstants.FILE_GROUP_GENERAL) String fileGroup,
                                    @RequestParam(name = "pointer", required = false, defaultValue = FileConstants.DEFAULT_FILE_POINTER) String pointer,
                                    @RequestParam(name = "rootDirectory", required = false, defaultValue = FileConstants.TEMP_ROOT_DIRECTORY) String rootDirectory,
                                    @RequestParam(name = "relativePath") String relativePath,
                                    @RequestParam("file") MultipartFile uploadedFile) {
        try {
            FileStoreEntryBaseDTO entry = FileUtils.createFileStoreEntry(uploadedFile, rootDirectory, relativePath);
            googleCaptchaService.validateCaptcha(captchaToken, request.getRemoteHost(), pointer);
            if (docTypeId != null) {
                DocTypeDTO dte = docTypeService.selectById(docTypeId);
                fileGroup = dte.getValidationFileGroup() == null ? fileGroup : dte.getValidationFileGroup();
            }
            return fileStoreService.saveNewFile(fileGroup, pointer, entry);
        } catch (IOException e) {
            log.error("File upload failed", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/save-new")
    public FileStoreEntryBaseDTO saveNewFile(@RequestParam(name = "fileGroup", required = false, defaultValue = FileConstants.FILE_GROUP_GENERAL) String fileGroup,
                                         @RequestParam(name = "pointer", required = false, defaultValue = FileConstants.DEFAULT_FILE_POINTER) String pointer,
                                         @RequestBody FileStoreEntryCreationRequestDTO storeRequest) {
        googleCaptchaService.validateCaptcha(storeRequest.getCaptchaToken(), storeRequest.getRemoteIp(), pointer);
        return fileStoreService.saveNewFile(fileGroup, pointer, storeRequest.getFileStoreEntry());
    }
}
