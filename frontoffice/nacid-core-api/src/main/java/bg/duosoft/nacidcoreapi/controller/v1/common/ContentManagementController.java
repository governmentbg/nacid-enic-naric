package bg.duosoft.nacidcoreapi.controller.v1.common;

import bg.duosoft.nacidcoreapi.integration.naciddoc.domain.NacidDocument;
import bg.duosoft.nacidcoreapi.integration.naciddoc.service.NacidDocumentService;
import bg.duosoft.nacidcoreapi.service.common.ContentManagementService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidcoredata.enums.ContentManagementId;
import bg.duosoft.nacidfrontofficedto.contentmgmt.ContentManagementDTO;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Api(tags = Tags.CONTENT_MANAGEMENT)
@RequestMapping("/api/v1/content-management")
@RequiredArgsConstructor
public class ContentManagementController {

    private final ContentManagementService contentManagementService;
    private final NacidDocumentService nacidDocumentService;

    @ApiOperation(value = "Select content management data by id")
    @GetMapping("/{id}")
    public String selectById(@PathVariable String id) {
        validationForViewRoles(id);
        return contentManagementService.findDataByIdAndActive(id);
    }

    @ApiOperation(value = "Select all by type")
    @GetMapping("")
    public List<ContentManagementDTO> selectByType(@RequestParam("type") String type) {
        List<ContentManagementDTO> resultList = contentManagementService.findByTypeAndActive(type);
        resultList.forEach(r -> {
            validationForViewRoles(r.getId());
        });
        return resultList;
    }

    @ApiOperation(value = "Update data by id")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).CONTENT_MANAGEMENT_EDIT)")
    public ContentManagementDTO update(@PathVariable("id") String id, @RequestBody String data) {
        validationForEditRoles(id);
        return contentManagementService.update(id, data);
    }

    @ApiOperation(value = "Get nacid description document content by service id")
    @GetMapping("/nacid/descriptiondoc")
    public ResponseEntity<byte[]> getNacidDescriptionDocumentContent(@RequestParam("id") String id) {
        NacidDocument nacidDoc = nacidDocumentService.getNacidDescriptionDocument(id);
        if(nacidDoc == null){
            return ResponseEntity.notFound().build();
        }
        URI uri = URI.create(nacidDoc.getFileUrl());
        byte[] content = nacidDocumentService.getNacidDocumentBytes(uri.getPath());
        if(content == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header("Content-Disposition", String.format("%s;filename*=UTF-8''%s;filename=\"%s\"", "attachment", "description.pdf", "description.pdf"))
                .contentType(MediaType.APPLICATION_PDF).body(content);
    }

    private void validationForViewRoles(String id) {
        ContentManagementId contentManagementId = getContentManagementById(id);
        List<String> accessRoles = contentManagementId.getAccessRolesOnView();
        if (!CollectionUtils.isEmpty(accessRoles)) {
            SecurityUtils.validateAnyRole(accessRoles);
        }
    }

    private void validationForEditRoles(String id) {
        ContentManagementId contentManagementId = getContentManagementById(id);
        List<String> accessRoles = contentManagementId.getAccessRolesOnEdit();
        SecurityUtils.validateAnyRole(accessRoles);
    }

    private ContentManagementId getContentManagementById(String id) {
        ContentManagementId contentManagementId = ContentManagementId.selectByCode(id);
        if (Objects.isNull(contentManagementId)) {
            throw new ResourceNotFoundException("Content management resource with ID = " + id + " doesn't exist");
        }
        return contentManagementId;
    }

}
