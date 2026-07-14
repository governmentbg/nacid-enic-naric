package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.gradingscaledetails;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GradingScaleDetailsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BaseGradingScaleDetailsClient {
    @GetMapping("/all")
    List<GradingScaleDetailsDTO> getAll();
    @GetMapping("/grading-scales/{id}")
    List<GradingScaleDetailsDTO> getByGradingScalesId(@PathVariable Integer id);

}
