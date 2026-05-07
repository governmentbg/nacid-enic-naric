package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.controller;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.CountryDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountriesController {

    private final CountriesService countriesService;

    @GetMapping
    public List<CountryDto> getActiveCountries() {
        return this.countriesService.getActiveCountries();
    }
}
