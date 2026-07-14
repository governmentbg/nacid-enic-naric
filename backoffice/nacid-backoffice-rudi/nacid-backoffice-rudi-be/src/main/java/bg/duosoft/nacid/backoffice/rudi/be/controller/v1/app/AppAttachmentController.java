package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.CommissionCalendarGlobalReportDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.ApplicationAttachmentService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidbackofficeshareddata.service.AbdocsAutoFileTransferService;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_ACCESS;
import static bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole.RUDI_APPLICATION_EDIT;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_ATTACHMENT)
@RequestMapping("/api/v1/applications/attachments")
public class AppAttachmentController extends BaseAccessController {
    private final ApplicationAttachmentService applicationAttachmentService;
    private final AbdocsAutoFileTransferService abdocsAutoFileTransferService;

    @Override
    public String getEditRole() {
        return RUDI_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return RUDI_APPLICATION_ACCESS;
    }

    @PostMapping(value = "save/{applicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Save application attachment")
    public void saveAttachment(@PathVariable Integer applicationId, @RequestBody AttachedDocDTO attachment) {
        applicationAttachmentService.saveAttachment(applicationId, attachment);
        abdocsAutoFileTransferService.transferApplicationFiles(applicationId);
    }

    @DeleteMapping({"/{id}"})
    @ApiOperation("Delete attachment")
    public void delete(@PathVariable("id") Integer id) {
        this.applicationAttachmentService.delete(id);
    }

    @PostMapping("/generate-global-report")
    public Map<String, FileStoreEntryBaseDTO> generateGlobalReport(@RequestBody CommissionCalendarGlobalReportDTO globalReportDTO) {
        Map<String, FileStoreEntryBaseDTO> stringFileStoreEntryBaseDTOMap = applicationAttachmentService.generateGlobalReport(globalReportDTO);
        if (!CollectionUtils.isEmpty(globalReportDTO.getApplicationIds()) && !globalReportDTO.getIsDraft()) {
            for (Integer id : globalReportDTO.getApplicationIds()) {
                abdocsAutoFileTransferService.transferApplicationFiles(id);
            }
        }
        return stringFileStoreEntryBaseDTOMap;
    }
}
