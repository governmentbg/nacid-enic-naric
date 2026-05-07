package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service;


import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.CountryDto;

import java.util.List;

public interface CountriesService {
    List<CountryDto> getActiveCountries();

    CountryDto getCountryByCountryCode(String countryCode);
}
