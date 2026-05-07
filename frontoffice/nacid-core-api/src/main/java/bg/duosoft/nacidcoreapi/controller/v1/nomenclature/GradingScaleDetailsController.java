package bg.duosoft.nacidcoreapi.controller.v1.nomenclature;


import bg.duosoft.nacidcoreapi.service.nomenclature.GradingScaleDetailsService;
import bg.duosoft.nacidcoreapi.util.swagger.Tags;
import bg.duosoft.nacidfrontofficedto.nomenclature.GradingScaleDetailsDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(tags = Tags.GRADING_SCALE_DETAILS)
@RestController
@RequestMapping("/api/v1/grading-scale-details")
@RequiredArgsConstructor
public class GradingScaleDetailsController {
    private final GradingScaleDetailsService gradingScaleDetailsService;

    @GetMapping("/by-scale-id")
    public List<GradingScaleDetailsDTO> getGradingScaleDetailsByScaleId(Integer scaleId) {
        return gradingScaleDetailsService.getGradingScaleDetailsByScaleId(scaleId);
    }
}
