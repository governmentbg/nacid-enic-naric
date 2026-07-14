package bg.duosoft.nacid.backoffice.rudi.client.client.university;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.autocomplete.UniversityAutocompleteDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.FacultyDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.UniversityFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.client.client.BaseCrudClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:18
 */
public interface UniversityBaseClient extends BaseCrudClient<Integer, UniversityDTO> {

    @GetMapping(value = "/search")
    Page<UniversityDTO> searchData(UniversityFilterDTO filter);


    @GetMapping(value = "/autocomplete")
    List<UniversityAutocompleteDTO> searchForAutocomplete(@RequestParam String bgName,
                                                          @RequestParam(required = false) Boolean active,
                                                          @RequestParam Integer page,
                                                          @RequestParam Integer pageSize);

    @GetMapping(value = "/autocomplete-faculties")
    List<FacultyDTO> searchFacultyForAutocomplete(@RequestParam Integer universityId,
                                                  @RequestParam String name,
                                                  @RequestParam(required = false, defaultValue = "false") Boolean onlyActive,
                                                  @RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer pageSize);

}
