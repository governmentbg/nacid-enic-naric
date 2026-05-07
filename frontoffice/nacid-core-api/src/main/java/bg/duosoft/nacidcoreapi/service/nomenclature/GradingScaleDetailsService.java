package bg.duosoft.nacidcoreapi.service.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.GradingScaleDetailsDTO;

import java.util.List;

public interface GradingScaleDetailsService {
    List<GradingScaleDetailsDTO> getGradingScaleDetailsByScaleId(Integer scaleId);
}
