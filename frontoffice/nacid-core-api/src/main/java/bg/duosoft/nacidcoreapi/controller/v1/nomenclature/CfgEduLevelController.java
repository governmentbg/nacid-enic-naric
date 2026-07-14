package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.CfgEduLevelService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.CfgEduLevelDTO;
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
 * Date: 02.12.2022
 * Time: 16:34
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.CFG_EDU_LEVEL)
@RequestMapping("/api/v1/cfg-edu-level")
public class CfgEduLevelController {

    private final CfgEduLevelService cfgEduLevelService;

    @GetMapping
    public List<CfgEduLevelDTO> getAll() {
        return cfgEduLevelService.getEduLevelsConfigs();
    }

    @GetMapping("/by-application-type-subtype")
    public List<CfgEduLevelDTO> getByApplicationTypeSubtype(@RequestParam ApplicationType applicationType, @RequestParam ApplicationSubtype applicationSubtype) {
        return cfgEduLevelService.getEduLevelsConfigsByApplicationTypeSubtype(applicationType, applicationSubtype);
    }
}
