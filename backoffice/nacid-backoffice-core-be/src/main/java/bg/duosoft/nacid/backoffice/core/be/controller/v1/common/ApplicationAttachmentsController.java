package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationAttachmentsService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.be.validation.common.AttachedDocumentValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_ATTACHED_DOCS)
@RequestMapping("/api/v1/application-attached-docs")
public class ApplicationAttachmentsController extends BaseAccessController {
    private final ApplicationAttachmentsService applicationAttachmentsService;
    private final AttachedDocumentValidator attachedDocumentValidator;

    @Override
    public String getEditRole() {
        return SecurityRole.CORE_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }


    @GetMapping
    @ApiOperation(value = "Select application attachments by applicationId and direction")
    public List<AttachedDocDTO> selectAttachments(@RequestParam Integer applicationId, @RequestParam(required = false) String direction, @RequestParam(required = false) Boolean finalized) {
        return applicationAttachmentsService.selectApplicationAttachments(applicationId, direction, finalized);
    }

    @GetMapping("/doc-category/{docCategory}")
    @ApiOperation(value = "Select application attachments by applicationId and direction and doc category")
    public List<AttachedDocDTO> selectAttachmentsByDocCategory(@PathVariable String docCategory, @RequestParam Integer applicationId, @RequestParam String direction) {
        return applicationAttachmentsService.selectAttachmentsByDocCategory(applicationId, direction, docCategory);
    }

    @GetMapping(value = "/doc/{id}")
    @ApiOperation(value = "select application attached doc by id ")
    public AttachedDocDTO selectById(@PathVariable Integer id) {
        return applicationAttachmentsService.selectById(id);
    }

    @PostMapping(value = "/validate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Validate application attachment")
    public void validateAttachment(@RequestBody AttachedDocDTO attachment) {
        BadRequestValidator.validateRequest(attachedDocumentValidator, attachment);
    }

}
