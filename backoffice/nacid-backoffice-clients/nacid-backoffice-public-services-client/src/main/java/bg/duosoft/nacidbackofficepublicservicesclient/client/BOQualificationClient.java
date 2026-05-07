package bg.duosoft.nacidbackofficepublicservicesclient.client;

import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.02.2023
 * Time: 13:37
 */
@FeignClient(name = "BOQualificationClient", url = "${feign.backoffice-public-services.base-url}/v1/qualification")
public interface BOQualificationClient {

    @GetMapping("/autocomplete")
    List<BaseAutocompleteDTO> autocompleteQualifications(@RequestParam String name,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer pageSize);
}
