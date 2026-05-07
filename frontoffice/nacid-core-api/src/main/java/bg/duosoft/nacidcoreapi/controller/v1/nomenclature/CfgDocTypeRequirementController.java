package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.CfgDocTypeRequirementService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeRequirementDTO;
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
 * Date: 26.01.2023
 * Time: 18:20
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.CFG_DOC_TYPE_REQUIREMENT)
@RequestMapping("/api/v1/cfg-doc-type-requirement")
public class CfgDocTypeRequirementController {

    private final CfgDocTypeRequirementService cfgDocTypeRequirementService;

    @GetMapping
    public List<CfgDocTypeRequirementDTO> getAll() {
        return cfgDocTypeRequirementService.getDocTypeRequirementConfigs();
    }

    @GetMapping("/for-app-type-subtype")
    public List<CfgDocTypeRequirementDTO> getAllByAppTypeAndSubtype(@RequestParam ApplicationType applicationType, @RequestParam(required = false) ApplicationSubtype applicationSubtype) {
        return cfgDocTypeRequirementService.getByApplicationTypeAndSubtype(applicationType, applicationSubtype);
    }

}
