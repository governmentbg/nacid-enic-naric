package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.CfgDocTypeService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgDocTypeDTO;
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
 * Date: 04.04.2023
 * Time: 16:13
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.CFG_DOC_TYPE)
@RequestMapping("/api/v1/cfg-doc-type")
public class CfgDocTypeController {

    private final CfgDocTypeService cfgDocTypeService;

    @GetMapping("/for-app-type-subtype")
    public List<CfgDocTypeDTO> getAllByAppTypeAndSubtype(@RequestParam ApplicationType applicationType,
                                                                     @RequestParam(required = false) ApplicationSubtype applicationSubtype){
        return cfgDocTypeService.getDocTypesConfigsByAppTypeAndSubtype(applicationType, applicationSubtype);
    }
}
