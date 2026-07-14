package bg.duosoft.nacidbackofficepublicservicesclient.client.regprof;

import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 22.06.2023
 * Time: 17:13
 */
@FeignClient(name = "BOProfessionNameClient", url = "${feign.backoffice-public-services.base-url}/v1/regprof/profession-name")
public interface BOProfessionNameClient {

    @GetMapping("/autocomplete")
    List<BaseAutocompleteDTO> autocompleteProfessionNames(@RequestParam String name,
                                                                 @RequestParam(required = false) Integer page,
                                                                 @RequestParam(required = false) Integer pageSize);
}
