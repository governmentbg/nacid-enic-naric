package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.impl;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.GradingScaleInfoDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.mapper.GradingScaleInfoMapper;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository.GradingScaleDetailsRepository;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.GradingScaleInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GradingScaleInfoServiceImpl implements GradingScaleInfoService {

    private final GradingScaleDetailsRepository gradingScaleDetailsRepository;
    private final GradingScaleInfoMapper gradingScaleInfoMapper;

    @Override
    public List<GradingScaleInfoDto> getGradingScaleInfo(Integer gradingScaleId) {

        return this.gradingScaleInfoMapper.toDtoList(this.gradingScaleDetailsRepository
                .getGradingScaleDetailsByGradingScaleId(gradingScaleId));

    }
}
