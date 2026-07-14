package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.ExternalNomenclaturesMapService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ExternalNomenclaturesMapDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_EXTERNAL_NOMENCLATURES_MAP)
@RequestMapping("/api/v1/external-nomenclatures-map")
public class ExternalNomenclaturesMapController extends BaseAccessController {

    private final ExternalNomenclaturesMapService service;

    @GetMapping
    @ApiOperation(value = "Select all external nomenclature map records by system, nomenclatureType, internalNomId")
    public List<ExternalNomenclaturesMapDTO> getAll(@RequestParam("system")String system, @RequestParam(value = "nomenclatureType") String nomenclatureType, @RequestParam("internalNomId")String internalNomId) {
        return service.selectBySystemNomenclatureTypeInternalNomId(system, nomenclatureType, internalNomId);
    }

    @PostMapping
    @ApiOperation(value = "Insert external nomenclatures map record")
    public ExternalNomenclaturesMapDTO create(@RequestBody ExternalNomenclaturesMapDTO dto) {
        return service.save(dto);
    }

    @PutMapping
    @ApiOperation(value = "Update external nomenclatures map record")
    public ExternalNomenclaturesMapDTO update(@RequestBody ExternalNomenclaturesMapDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete all external nomenclatures map records (or by system)")
    public void deleteAll(@RequestParam(value = "system", required = false) String system) {
        if (StringUtils.isEmpty(system)) {
            service.deleteAll();
        } else {
            service.deleteBySystem(system);
        }
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
