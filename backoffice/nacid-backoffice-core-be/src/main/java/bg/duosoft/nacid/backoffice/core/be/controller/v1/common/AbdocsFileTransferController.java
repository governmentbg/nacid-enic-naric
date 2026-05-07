package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.AbdocsFileTransferService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_ABDOCS_FILE_TRANSFER)
@RequestMapping("/api/v1/application-abdocs-file-transfer")
public class AbdocsFileTransferController extends BaseAccessController {
    private final AbdocsFileTransferService abdocsFileTransferService;

    @Override
    public String getEditRole() {
        return SecurityRole.CORE_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @PostMapping
    public AttachedDocDTO abdocsTransferAttachedDocFiles(@RequestParam Integer applicationId, @RequestParam Integer attachedDocId) {
        return abdocsFileTransferService.transferApplicationAttachment(applicationId, attachedDocId);
    }
}
