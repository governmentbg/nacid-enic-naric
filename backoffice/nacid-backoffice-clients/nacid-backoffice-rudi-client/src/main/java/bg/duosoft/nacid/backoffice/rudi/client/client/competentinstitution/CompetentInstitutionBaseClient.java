package bg.duosoft.nacid.backoffice.rudi.client.client.competentinstitution;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CompetentInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.CompetentInstitutionFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.client.client.BaseCrudClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:18
 */
public interface CompetentInstitutionBaseClient extends BaseCrudClient<Integer, CompetentInstitutionDTO> {

    @GetMapping(value = "/search")
    Page<CompetentInstitutionDTO> searchData(CompetentInstitutionFilterDTO filter);


}
