package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgEducationLevelToApplicationTypeService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgEduLevelToAppTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgEduLevelToAppTypeFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshared.web.util.json.JsonUtil;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * User: ggeorgiev
 * Date: 15.09.2022
 * Time: 18:19
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_CFG_EDU_LEVEL_TO_APP_TYPE)
@RequestMapping("/api/v1/cfg-edu-level-to-app-type")
public class CfgEducationLevelToAppTypeController extends BaseAccessController /*CrudController<Integer, CfgEduLevelToAppTypeDTO>*/ {
    private final CfgEducationLevelToApplicationTypeService service;
    private final ApplicationsService applicationsService;

    @Autowired
    protected JsonUtil jsonUtil;

    @GetMapping("/all")
    @ApiOperation("Select all")
    public List<CfgEduLevelToAppTypeDTO> getAll() {
        return service.getAll();
    }

    @GetMapping({"/{ell}/{ate}/{ase}"})
    @ApiOperation("Select single value")
    public CfgEduLevelToAppTypeDTO getById(@PathVariable("ell") String ell, @PathVariable("ate") String ate, @PathVariable("ase") String ase) {
        CfgEduLevelToAppTypeDTO result = service.selectById(ell, ate, ase);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        } else {
            return result;
        }
    }

    @PostMapping
    @ApiOperation(value = "Insert value")
    public CfgEduLevelToAppTypeDTO create(@RequestBody CfgEduLevelToAppTypeDTO dto) {
        return service.insert(dto);
    }

    @GetMapping
    @ApiOperation(value = "Get by application type and subtype")
    public List<ReferenceDataDTO> get(@RequestParam("applicationType") String applicationType, @RequestParam("applicationSubtype") String applicationSubtype) {
        return service.selectByApplicationTypeSubtype(applicationType, applicationSubtype);
    }

    @GetMapping(value = "/by-application/{applicationId}")
    @ApiOperation(value = "Get by application id")
    public List<ReferenceDataDTO> get(@PathVariable("applicationId") Integer applicationId) {
        Pair<String, String> pair = applicationsService.getAppTypeAndSubtypeById(applicationId);
        if (Objects.isNull(pair)) {
            throw new ResourceNotFoundException();
        }
        String applicationType = pair.getFirst();
        String applicationSubtype = pair.getSecond();
        return service.selectByApplicationTypeSubtype(applicationType, applicationSubtype);
    }

    @GetMapping(value = "/select-by-app-types")
    @ApiOperation(value = "Select records by app type and app sub type")
    public List<ReferenceDataDTO> selectByAppTypes(String appType, String appSubTypes) {
        List<ReferenceDataDTO> eduLevels = service.selectByApplicationTypeSubtypes(appType, jsonUtil.readJsonList(appSubTypes, String.class));
        return eduLevels;
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter nomenclature records")
    public Page<CfgEduLevelToAppTypeDTO> searchData(CfgEduLevelToAppTypeFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<CfgEduLevelToAppTypeDTO> data = service.selectData(filter);
        return new Page<>(service.selectCountData(filter), data, filter.getPageSize());
    }

    @DeleteMapping({"/{ell}/{ate}/{ase}"})
    @ApiOperation("Delete value")
    public void delete(@PathVariable("ell") String ell, @PathVariable("ate") String ate, @PathVariable("ase") String ase) {
        service.delete(ell, ate, ase);
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
