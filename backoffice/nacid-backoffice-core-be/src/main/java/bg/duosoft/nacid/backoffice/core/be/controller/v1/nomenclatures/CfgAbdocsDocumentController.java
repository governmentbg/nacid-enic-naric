package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgAbdocsDocumentService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAbdocsDocumentDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_CFG_ABDOCS_DOCUMENT)
@RequestMapping("/api/v1/cfg-abdocs-documents")
public class CfgAbdocsDocumentController extends BaseAccessController {

    private final CfgAbdocsDocumentService cfgAbdocsDocumentService;

    @GetMapping
    @ApiOperation(value = "Get by application type and subtype")
    public CfgAbdocsDocumentDTO getByAppTypeAndSubType(@RequestParam("appType") String appType, @RequestParam("appSubType") String appSubType) {
        CfgAbdocsDocumentDTO cfgAbdocsDocumentDTO = cfgAbdocsDocumentService.selectByAppTypeAndSubType(appType, appSubType);
        if (Objects.isNull(cfgAbdocsDocumentDTO)) {
            throw new ResourceNotFoundException();
        }

        return cfgAbdocsDocumentDTO;
    }

    @GetMapping("/by-type")
    @ApiOperation(value = "Get by application type ")
    public CfgAbdocsDocumentDTO getByAppType(@RequestParam("appType") String appType) {
        CfgAbdocsDocumentDTO cfgAbdocsDocumentDTO = cfgAbdocsDocumentService.selectByAppType(appType);
        if (Objects.isNull(cfgAbdocsDocumentDTO)) {
            throw new ResourceNotFoundException();
        }

        return cfgAbdocsDocumentDTO;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get by Id")
    public CfgAbdocsDocumentDTO getById(@PathVariable("id") String id) {
        CfgAbdocsDocumentDTO cfgAbdocsDocumentDTO = cfgAbdocsDocumentService.selectById(id);
        if (Objects.isNull(cfgAbdocsDocumentDTO)) {
            throw new ResourceNotFoundException();
        }

        return cfgAbdocsDocumentDTO;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }
}
