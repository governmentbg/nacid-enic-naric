package bg.duosoft.nacid.backoffice.rudi.client.client.traininginstitution;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.TrainingInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.TrainingInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.client.client.BaseCrudClient;
import org.springframework.web.bind.annotation.GetMapping;

public interface TrainingInstitutionBaseClient extends BaseCrudClient<Integer, TrainingInstitutionDTO> {

    @GetMapping(value = "/search")
    Page<TrainingInstitutionDTO> searchData(TrainingInstitutionFilterDTO filter);


}
