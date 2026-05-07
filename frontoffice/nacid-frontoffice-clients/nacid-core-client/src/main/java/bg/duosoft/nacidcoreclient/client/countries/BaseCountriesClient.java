package bg.duosoft.nacidcoreclient.client.countries;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:18
 */
public interface BaseCountriesClient {

    @GetMapping
    List<CountryDTO> selectCountries();

    @GetMapping(value = "/{id}")
    CountryDTO selectById(@RequestParam("id") String id);

    @PostMapping
    CountryDTO createCountry(@RequestBody CountryDTO country);

    @PutMapping
    CountryDTO updateCountry(@RequestBody CountryDTO country);

    @DeleteMapping(value = "/{id}")
    void deleteCountry(@RequestParam("id") String id);
}
