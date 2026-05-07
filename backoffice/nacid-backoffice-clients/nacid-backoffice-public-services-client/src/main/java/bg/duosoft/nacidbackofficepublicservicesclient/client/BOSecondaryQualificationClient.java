package bg.duosoft.nacidbackofficepublicservicesclient.client;

import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.03.2023
 * Time: 14:01
 */
@FeignClient(name = "BOSecondaryQualificationClient", url = "${feign.backoffice-public-services.base-url}/v1/secondary-qualification")
public interface BOSecondaryQualificationClient {

    @GetMapping("/autocomplete")
    List<BaseAutocompleteDTO> autocompleteSecondaryQualification(@RequestParam(required = false, defaultValue = "true") Boolean onlyActive,
                                                                      @RequestParam String name,
                                                                      @RequestParam(required = false) Integer page,
                                                                      @RequestParam(required = false) Integer pageSize);
}
