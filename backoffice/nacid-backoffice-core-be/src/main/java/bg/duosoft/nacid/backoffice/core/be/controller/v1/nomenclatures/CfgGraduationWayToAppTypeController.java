package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgGraduationWayToApplicationTypeService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgGraduationWayToAppTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgGraduationWayToAppTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshared.web.util.json.JsonUtil;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 19.09.2022
 * Time: 18:19
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_CFG_GRADUATION_WAY_TO_APP_TYPE)
@RequestMapping("/api/v1/cfg-graduation-way-to-app-type")
public class CfgGraduationWayToAppTypeController extends BaseAccessController {
    private final CfgGraduationWayToApplicationTypeService service;

    @Autowired
    protected JsonUtil jsonUtil;

    @GetMapping("/all")
    public List<CfgGraduationWayToAppTypeDTO> getAll() {
        return service.getAll();
    }

    @GetMapping({"/{gwy}/{ate}/{ase}"})
    @ApiOperation("Select single value")
    public CfgGraduationWayToAppTypeDTO getById(@PathVariable("gwy") String gwy, @PathVariable("ate") String ate, @PathVariable("ase") String ase) {
        CfgGraduationWayToAppTypeDTO result = service.selectById(gwy, ate, ase);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        } else {
            return result;
        }
    }

    @PostMapping
    @ApiOperation(value = "Insert nomenclature value")
    public CfgGraduationWayToAppTypeDTO create(@RequestBody CfgGraduationWayToAppTypeDTO dto) {
        return service.insert(dto);
    }
    @GetMapping
    @ApiOperation(value = "Get by application type and subtype")
    public List<ReferenceDataDTO> get(@RequestParam("applicationType")String applicationType, @RequestParam("applicationSubtype")String applicationSubtype) {
        return service.selectByApplicationTypeSubtype(applicationType, applicationSubtype);
    }

    @GetMapping(value = "/select-by-app-types")
    @ApiOperation(value = "Select records by app type and app sub type")
    public List<ReferenceDataDTO> selectByAppTypes(String appType, String appSubTypes) {
        List<ReferenceDataDTO> graduationWays = service.selectByApplicationTypeSubtypes(appType, jsonUtil.readJsonList(appSubTypes, String.class));
        return graduationWays;
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter nomenclature records")
    public Page<CfgGraduationWayToAppTypeDTO> searchData(CfgGraduationWayToAppTypeFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<CfgGraduationWayToAppTypeDTO> data = service.selectData(filter);
        return new Page<>(service.selectCountData(filter), data, filter.getPageSize());
    }

    @DeleteMapping({"/{gwy}/{ate}/{ase}"})
    @ApiOperation("Delete value")
    public void delete(@PathVariable("gwy") String gwy, @PathVariable("ate") String ate, @PathVariable("ase") String ase) {
        service.delete(gwy, ate, ase);
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @Override
    public String getAccessRole() {
        return SecurityRole.BO_NOMENCLATURES_ACCESS;
    }
}
