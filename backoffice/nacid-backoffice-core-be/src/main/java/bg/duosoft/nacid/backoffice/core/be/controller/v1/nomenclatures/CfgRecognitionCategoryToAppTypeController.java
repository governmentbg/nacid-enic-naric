package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CfgRecognitionCategoryToApplicationTypeService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CfgRecognitionCategoryToAppTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CfgRecognitionCategoryToAppTypeFilterDTO;
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
 * Date: 30.05.2023
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_CFG_EDU_LEVEL_TO_APP_TYPE)
@RequestMapping("/api/v1/cfg-recognition-category-to-app-type")
public class CfgRecognitionCategoryToAppTypeController extends BaseAccessController /*CrudController<Integer, CfgRecognitionCategoryToAppTypeDTO>*/ {
    private final CfgRecognitionCategoryToApplicationTypeService service;

    @Autowired
    protected JsonUtil jsonUtil;

    @GetMapping("/all")
    @ApiOperation("Select all")
    public List<CfgRecognitionCategoryToAppTypeDTO> getAll() {
        return service.getAll();
    }

    @GetMapping({"/{rcy}/{ate}/{ase}"})
    @ApiOperation("Select single value")
    public CfgRecognitionCategoryToAppTypeDTO getById(@PathVariable("rcy") String rcy, @PathVariable("ate") String ate, @PathVariable("ase") String ase) {
        CfgRecognitionCategoryToAppTypeDTO result = service.selectById(rcy, ate, ase);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        } else {
            return result;
        }
    }

    @PostMapping
    @ApiOperation(value = "Insert value")
    public CfgRecognitionCategoryToAppTypeDTO create(@RequestBody CfgRecognitionCategoryToAppTypeDTO dto) {
        return service.insert(dto);
    }

    @GetMapping
    @ApiOperation(value = "Get by application type and subtype")
    public List<ReferenceDataDTO> get(@RequestParam("applicationType") String applicationType, @RequestParam("applicationSubtype") String applicationSubtype) {
        return service.selectByApplicationTypeSubtype(applicationType, applicationSubtype);
    }

    @GetMapping(value = "/select-by-app-types")
    @ApiOperation(value = "Select records by app type and app sub type")
    public List<ReferenceDataDTO> selectByAppTypes(String appType, String appSubTypes) {
        List<ReferenceDataDTO> RecognitionCategorys = service.selectByApplicationTypeSubtypes(appType, jsonUtil.readJsonList(appSubTypes, String.class));
        return RecognitionCategorys;
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Filter nomenclature records")
    public Page<CfgRecognitionCategoryToAppTypeDTO> searchData(CfgRecognitionCategoryToAppTypeFilterDTO filter) {
        filter.setPage(filter.getPage() + 1);
        List<CfgRecognitionCategoryToAppTypeDTO> data = service.selectData(filter);
        return new Page<>(service.selectCountData(filter), data, filter.getPageSize());
    }

    @DeleteMapping({"/{rcy}/{ate}/{ase}"})
    @ApiOperation("Delete value")
    public void delete(@PathVariable("rcy") String rcy, @PathVariable("ate") String ate, @PathVariable("ase") String ase) {
        service.delete(rcy, ate, ase);
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
