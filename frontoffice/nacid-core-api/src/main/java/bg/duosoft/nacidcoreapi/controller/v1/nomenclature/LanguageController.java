package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base.NomenclatureSearchBaseController;
import bg.duosoft.nacidcoreapi.service.nomenclature.LanguageService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.LanguageDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.LanguageFilterDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 04.10.2022
 * Time: 13:46
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_LANGUAGE)
@RequestMapping("/api/v1/language")
public class LanguageController extends NomenclatureSearchBaseController<String, LanguageDTO, LanguageFilterDTO> {

    private final LanguageService service;

    @Override
    protected NomenclatureServiceBase<String, LanguageDTO, LanguageFilterDTO> getService() {
        return service;
    }
}