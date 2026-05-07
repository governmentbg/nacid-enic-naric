package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.impl;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.CountryDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.mapper.CountriesMapper;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository.CountryRepository;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CountriesServiceImpl implements CountriesService {

    private final CountryRepository countryRepository;
    private final CountriesMapper countriesMapper;

    @Override
    public List<CountryDto> getActiveCountries() {
        return this.countriesMapper
                .toDtoList(this.countryRepository.getActiveCountries());
    }

    @Override
    public CountryDto getCountryByCountryCode(String countryCode) {
        return this.countriesMapper
                .toDto(this.countryRepository.getCountryByCode(countryCode));
    }
}
