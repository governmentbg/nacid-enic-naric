package bg.duosoft.nacidbackofficepublicservicesclient.client;

import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.06.2023
 * Time: 13:19
 */
@FeignClient(name = "BOOriginalSpecialityClient", url = "${feign.backoffice-public-services.base-url}/v1/original-speciality")
public interface BOOriginalSpecialityClient {

    @GetMapping("/autocomplete")
    List<BaseAutocompleteDTO> autocompleteOriginalSpecialities(@RequestParam String name,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer pageSize);
}
