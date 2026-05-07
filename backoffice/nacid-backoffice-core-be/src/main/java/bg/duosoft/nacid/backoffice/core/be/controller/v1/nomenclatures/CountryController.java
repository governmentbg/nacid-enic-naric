package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures;

import bg.duosoft.nacidbackofficeshareddata.controller.NomenclatureBaseController;
import bg.duosoft.nacid.backoffice.core.be.service.nomenclature.CountryService;
import bg.duosoft.nacidbackofficeshareddata.service.NomenclatureServiceBase;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.BaseNomenclatureDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CountryFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.NOM_COUNTRY)
@RequestMapping("/api/v1/countries")
public class CountryController extends NomenclatureBaseController<String, CountryDTO, CountryFilterDTO> {

    private final CountryService service;

    @Override
    protected NomenclatureServiceBase<String, CountryDTO, CountryFilterDTO> getService() {
        return service;
    }

    @Override
    public String getEditRole() {
        return SecurityRole.BO_NOMENCLATURES_EDIT;
    }

    @GetMapping(value = "/autocomplete")
    @ApiOperation(value = "Select countries autocomplete")
    public List<BaseNomenclatureDTO> selectCountries() {
        List<CountryDTO> countries = service.selectAll(true);
        return countries.stream()
                .map(o -> BaseNomenclatureDTO.newInstance(o.getId(), o.getName(), o.getName(), o.getIsActive(), null))
                .collect(Collectors.toList());
    }

}
