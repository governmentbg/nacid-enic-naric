package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;

import bg.duosoft.nacidcoreapi.service.nomenclature.GradingScaleService;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.GradingScaleDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = Tags.GRADING_SCALES)
@RestController
@RequestMapping("/api/v1/grading-scales")
@RequiredArgsConstructor
public class GradingScaleController {

    private final GradingScaleService gradingScaleService;


    @GetMapping("/countries")
    public List<CountryDTO> getGradingScaleCountries() {
        return gradingScaleService.getGradingScaleCountries()
                .stream()
                .sorted(Comparator.comparing(CountryDTO::getName, Comparator.nullsLast(String::compareToIgnoreCase)))
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<GradingScaleDTO> getGradingScalesByCountryCodeAndYear(String countryCode, String year) {
        List<GradingScaleDTO> resultList = new ArrayList<>();
        if (StringUtils.hasText(year) && year.matches("\\d+")) {
            int numericYear = Integer.parseInt(year);
            resultList.addAll(gradingScaleService.getGradingScalesByCountryCodeAndYear(countryCode, numericYear));
        }
        return resultList;
    }
}
