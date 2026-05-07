package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.controller.v1.nomenclature.base.NomenclatureSearchBaseController;
import bg.duosoft.nacidcoreapi.service.nomenclature.CountryService;
import bg.duosoft.nacidcoreapi.service.nomenclature.base.NomenclatureServiceBase;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.CountryFilterDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_COUNTRY)
@RequestMapping("/api/v1/countries")
public class CountryController extends NomenclatureSearchBaseController<String, CountryDTO, CountryFilterDTO> {

    private final CountryService service;

    @Override
    protected NomenclatureServiceBase<String, CountryDTO, CountryFilterDTO> getService() {
        return service;
    }

}