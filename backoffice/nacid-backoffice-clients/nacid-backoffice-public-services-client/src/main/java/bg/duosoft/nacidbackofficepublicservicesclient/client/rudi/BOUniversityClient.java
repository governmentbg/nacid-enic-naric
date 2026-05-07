package bg.duosoft.nacidbackofficepublicservicesclient.client.rudi;

import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.02.2023
 * Time: 13:19
 */
@FeignClient(name = "BOUniversityClient", url = "${feign.backoffice-public-services.base-url}/v1/rudi/universities")
public interface BOUniversityClient {

    @GetMapping(value = "/autocomplete")
    List<BaseAutocompleteDTO> searchForAutocomplete(@RequestParam String name,
                                                                   @RequestParam(required = false, defaultValue = "true") Boolean onlyActive,
                                                                   @RequestParam(required = false) Integer page,
                                                                   @RequestParam(required = false) Integer pageSize);

    @GetMapping(value = "/autocomplete-faculties")
    List<BaseAutocompleteDTO> searchFacultyForAutocomplete(@RequestParam Integer universityId, @RequestParam String name,
                                                                          @RequestParam(required = false, defaultValue = "true") Boolean onlyActive,
                                                                          @RequestParam(required = false) Integer page,
                                                                          @RequestParam(required = false) Integer pageSize);
}
