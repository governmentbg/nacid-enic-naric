package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.core.be.service.common.AbdocsCoreService;
import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationAttachmentsService;
import bg.duosoft.nacid.backoffice.core.be.service.common.CorrespondenceDocsService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.CORRESPONDENCE_DOCS)
@RequestMapping("/api/v1/correspondence-docs")
public class CorrespondenceDocsController {
    private final ApplicationAttachmentsService applicationAttachmentsService;
    private final AbdocsCoreService abdocsCoreService;
    private final CorrespondenceDocsService correspondenceDocsService;

    @GetMapping("/abdocs-doc-by-attached-doc-id")
    @ApiOperation(value = "Select Abdocs doc by attached doc id")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).PUBLIC_SERVICES_ACCESS)")
    public Doc selectAbdocsDocByAttachedDocId(@RequestParam Integer attachedDocId) {
        AttachedDocDTO attachedDocDTO = applicationAttachmentsService.selectById(attachedDocId);
        if (Objects.isNull(attachedDocDTO)) {
            throw new RuntimeException("Cannot find attached document with ID = " + attachedDocId);
        }
        if (!StringUtils.hasText(attachedDocDTO.getDocflowId())) {
            throw new RuntimeException("No docflow ID for document with ID = " + attachedDocId);
        }
        return abdocsCoreService.selectAbdocsDoc(Integer.valueOf(attachedDocDTO.getDocflowId()));
    }

    @PostMapping("/process-correspondence-docs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Process correspondence docs")
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).BO_CRON_ACCESS)")
    public void processCorrespondenceDocs() {
        correspondenceDocsService.processCorrespondenceDocs();
    }

}
