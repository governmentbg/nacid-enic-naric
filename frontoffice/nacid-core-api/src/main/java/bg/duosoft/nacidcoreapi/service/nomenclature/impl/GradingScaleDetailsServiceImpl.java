package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.GradingScaleDetailsRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.GradingScaleDetailsService;
import bg.duosoft.nacidfrontofficedto.nomenclature.GradingScaleDetailsDTO;
import bg.duosoft.nacidcoredata.mapper.nomenclature.GradingScaleDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradingScaleDetailsServiceImpl implements GradingScaleDetailsService {
    private final GradingScaleDetailsMapper gradingScaleDetailsMapper;
    private final GradingScaleDetailsRepository gradingScaleDetailsRepository;

    @Override
    public List<GradingScaleDetailsDTO> getGradingScaleDetailsByScaleId(Integer scaleId) {
        return gradingScaleDetailsMapper.toDtoList(gradingScaleDetailsRepository.getGradingScaleDetailsByScaleId(scaleId));
    }
}
