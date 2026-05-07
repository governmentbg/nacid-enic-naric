package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ContentManagementService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ContentManagementDTO;
import bg.duosoft.nacid.backoffice.core.data.util.enums.ContentManagementId;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@Api(tags = Tags.CONTENT_MANAGEMENT)
@RequestMapping("/api/v1/content-management")
@RequiredArgsConstructor
public class ContentManagementController {

    private final ContentManagementService contentManagementService;

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
    @PreAuthorize("hasRole(T(bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole).CONTENT_MANAGEMENT_EDIT)")
    public ContentManagementDTO update(@PathVariable("id") String id, @RequestBody String data) {
        validationForEditRoles(id);
        return contentManagementService.update(id, data);
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
