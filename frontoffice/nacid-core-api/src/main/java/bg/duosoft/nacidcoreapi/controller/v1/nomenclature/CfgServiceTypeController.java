package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.CfgServiceTypeService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgServiceTypeDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationType;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 25.01.2023
 * Time: 11:18
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.CFG_SERVICE_TYPE)
@RequestMapping("/api/v1/cfg-service-type")
public class CfgServiceTypeController {

    private final CfgServiceTypeService cfgServiceTypeService;

    @GetMapping
    public List<CfgServiceTypeDTO> getAll() {
        return cfgServiceTypeService.getServiceTypesConfigs();
    }

    @GetMapping("/by-app-type-subtype")
    public List<CfgServiceTypeDTO> getAllByApplicationTypeSubtype(@RequestParam ApplicationType applicationType, @RequestParam ApplicationSubtype applicationSubtype, @RequestParam(defaultValue = "true", required = false)boolean onlyActive) {
        return cfgServiceTypeService.getServiceTypesConfigsByApplicationTypeSubtype(applicationType, applicationSubtype, onlyActive);
    }

}
