package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service;


import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.GradingScaleInfoDto;

import java.util.List;

public interface GradingScaleInfoService {

    List<GradingScaleInfoDto> getGradingScaleInfo(Integer gradingScaleId);
}
