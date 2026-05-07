package bg.duosoft.nacidbackofficepublicservicesclient.client;

import bg.duosoft.nacidfrontofficedto.Page;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.CountryFilterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "BOCountriesClient", url = "${feign.backoffice-public-services.base-url}/v1/countries")
public interface BOCountriesClient {

    @GetMapping
    List<CountryDTO> selectCountries(@RequestParam(value = "onlyActive", defaultValue = "true") boolean onlyActive);

    @GetMapping(value = "/search")
    Page<CountryDTO> searchData(@SpringQueryMap CountryFilterDTO filter);

}
