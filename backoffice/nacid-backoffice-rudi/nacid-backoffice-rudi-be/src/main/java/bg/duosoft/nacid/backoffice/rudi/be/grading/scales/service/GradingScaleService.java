package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service;


import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.GradingScaleDto;

import java.util.List;

public interface GradingScaleService {
    List<GradingScaleDto> getGradingScalesByCountryCodeAndYear(String countryCode, Integer year);
}
