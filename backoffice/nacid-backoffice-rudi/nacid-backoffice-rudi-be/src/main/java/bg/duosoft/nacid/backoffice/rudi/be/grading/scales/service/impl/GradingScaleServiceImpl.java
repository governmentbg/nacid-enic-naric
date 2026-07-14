package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.impl;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.GradingScaleDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.mapper.GradingScalesMapper;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository.GradingScaleRepository;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.GradingScaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GradingScaleServiceImpl implements GradingScaleService {

    private final GradingScaleRepository gradingScalesRepository;
    private final GradingScalesMapper gradingScalesMapper;


    @Override
    public List<GradingScaleDto> getGradingScalesByCountryCodeAndYear(String countryCode, Integer year) {
        return this.gradingScalesMapper
                .toDtoList(this.gradingScalesRepository.getGradingScalesByCountryCodeAndYear(countryCode, year));
    }
}
