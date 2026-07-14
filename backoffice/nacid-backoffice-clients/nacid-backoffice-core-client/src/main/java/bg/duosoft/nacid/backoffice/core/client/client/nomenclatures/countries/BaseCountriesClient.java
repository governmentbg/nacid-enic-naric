package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.countries;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.CountryFilterDTO;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:18
 */
public interface BaseCountriesClient extends BaseNomenclaturesClient<String, CountryDTO> {

    @GetMapping(value = "/search")
    Page<CountryDTO> searchData(@SpringQueryMap CountryFilterDTO filter);
}
