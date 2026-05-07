package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidcoredata.util.FileUtils;
import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationDetailsForSignDTO;
import bg.duosoft.nacidservicesbe.controller.utils.AccessUtils;
import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import bg.duosoft.nacidservicesbe.service.FileService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.02.2023
 * Time: 14:17
 */
@Api(tags = Tags.APP_SIGN)
@RestController
@RequestMapping("/api/v1/app-sign")
@RequiredArgsConstructor
@Slf4j
public class ApplicationSignController {

    private final FileService fileService;
    private final CommonApplicationService commonApplicationService;

    @GetMapping("/fetch-details-to-sign")
    public ApplicationDetailsForSignDTO fetchDetailsToSign(@RequestParam Integer id){
        AccessUtils.checkAccessAllowedForAppModification(id, commonApplicationService);
        AccessUtils.checkAppFilingSignedAllowedDependingOnStatus(id, commonApplicationService);
        ApplicationDetailsForSignDTO signDetails = commonApplicationService.getApplicationSignDetails(id);
        if(signDetails == null){
            throw new ResourceNotFoundException();
        }
        return signDetails;
    }

    @PostMapping("/upload")
    @PreAuthorize("isAuthenticated()")
    public FileStoreEntryDTO uploadSignedAppFile(@RequestParam(name = "relativePath") String relativePath,
                                              @RequestParam("file") MultipartFile uploadedFile) {
        try {
            //TODO special pdf validation to get signature, validate it and so on
            FileStoreEntryDTO entry = FileUtils.createFileStoreEntry(uploadedFile, FileConstants.TEMP_ROOT_DIRECTORY, relativePath);
            return fileService.uploadFile(FileConstants.FILE_GROUP_PDF, entry);
        } catch (IOException e) {
            log.error("File upload failed", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
