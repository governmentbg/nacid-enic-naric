package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.GradingScaleRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.GradingScaleService;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.GradingScaleEntity;
import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.GradingScaleDTO;
import bg.duosoft.nacidcoredata.mapper.nomenclature.GradingScaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradingScaleServiceImpl implements GradingScaleService {
    private final GradingScaleMapper gradingScaleMapper;
    private final GradingScaleRepository gradingScaleRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<GradingScaleDTO> getGradingScalesByCountryCodeAndYear(String countryCode, Integer year) {
        List<GradingScaleEntity> gradingScales = gradingScaleRepository.getGradingScalesByAlternateKeyAndYear(countryCode, year);
        if (!CollectionUtils.isEmpty(gradingScales)) {
            return gradingScaleMapper.toDtoList(gradingScales);
        }

        return gradingScaleMapper.toDtoList(gradingScaleRepository.getGradingScalesByCountryCodeAndYear(countryCode, year));
    }

    @Override
    public List<CountryDTO> getGradingScaleCountries() {
        return countryMapper.toDtoList(gradingScaleRepository.getGradingScaleCountries());
    }
}
