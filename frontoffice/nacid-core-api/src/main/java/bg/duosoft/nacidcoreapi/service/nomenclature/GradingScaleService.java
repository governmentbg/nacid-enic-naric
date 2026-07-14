package bg.duosoft.nacidcoreapi.service.nomenclature;


import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.GradingScaleDTO;
import java.util.List;

public interface GradingScaleService {
    List<GradingScaleDTO> getGradingScalesByCountryCodeAndYear(String countryCode, Integer year);
    List<CountryDTO> getGradingScaleCountries();
}
