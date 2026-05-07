package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgAppStatusService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgAppStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgSarAppStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:59
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_APPLICATION_TYPE)
@RequestMapping("/api/v1/cfg-application-status")
public class CfgAppStatusController extends BaseAccessController {

    private final CfgAppStatusService cfgAppStatusService;

    @DeleteMapping(value = "/delete-all")
    @ApiOperation(value = "Delete all \"app status\" and \"sar app status\"  config values")
    public void deleteAll() {
        cfgAppStatusService.deleteAll();
    }


    @PostMapping
    @ApiOperation(value = "Insert app status config value")
    public CfgAppStatusDTO create(@RequestBody CfgAppStatusDTO dto) {
        return cfgAppStatusService.insert(dto);
    }

    @PostMapping("/sar")
    @ApiOperation(value = "Insert statute / authenticity / recommendation / app status config  value")
    public CfgSarAppStatusDTO create(@RequestBody CfgSarAppStatusDTO dto) {
        return cfgAppStatusService.insert(dto);
    }

    @GetMapping("/sar")
    @ApiOperation(value = "Select all statute / authenticity / recommendation / app status configurations")
    public List<CfgSarAppStatusDTO> getAllSarConfigs() {
        return cfgAppStatusService.selectAllSarAppStatuses();
    }

    @GetMapping("/sar/by-status/{statusCode}")
    @ApiOperation(value = "Select statute / authenticity / recommendation / app status configurations by status")
    public List<CfgSarAppStatusDTO> getSarConfigsByStatus(@PathVariable("statusCode") String statusCode) {
        return cfgAppStatusService.selectSarAppConfigByStatus(statusCode);
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
