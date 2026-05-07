package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.LanguageService;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LanguageDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.LanguageFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_LANGUAGE)
@RequestMapping("/api/v1/languages")
public class LanguageController extends NomenclatureBaseController<String, LanguageDTO, LanguageFilterDTO> {

    private final LanguageService service;

    @GetMapping(value = "/select-by-app-types")
    @ApiOperation(value = "Select language records by app type and app sub type")
    public List<LanguageDTO> selectByAppTypes(@RequestParam("appType") String appType, @RequestParam("appSubType") String appSubType) {
        List<LanguageDTO> languages = service.selectByApplicationTypeSubtype(appType, appSubType);
        return languages;
    }

    @Override
    protected NomenclatureServiceBase<String, LanguageDTO, LanguageFilterDTO> getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }
}
