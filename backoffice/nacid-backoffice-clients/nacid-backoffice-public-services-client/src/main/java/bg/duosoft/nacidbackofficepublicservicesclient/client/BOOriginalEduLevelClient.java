package bg.duosoft.nacidbackofficepublicservicesclient.client;

import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 18:34
 */
@FeignClient(name = "BOOriginalEduLevelClient", url = "${feign.backoffice-public-services.base-url}/v1/original-edu-level")
public interface BOOriginalEduLevelClient {

    @GetMapping("/autocomplete")
    List<BaseAutocompleteDTO> autocompleteOriginalEduLevels(@RequestParam String name,
                                                                 @RequestParam(required = false) Integer page,
                                                                 @RequestParam(required = false) Integer pageSize);

    @GetMapping("/autocomplete-translated")
    List<BaseAutocompleteDTO> autocompleteOriginalEduLevelsTranslated(@RequestParam String name,
                                                                           @RequestParam(required = false) Integer page,
                                                                           @RequestParam(required = false) Integer pageSize);
}
