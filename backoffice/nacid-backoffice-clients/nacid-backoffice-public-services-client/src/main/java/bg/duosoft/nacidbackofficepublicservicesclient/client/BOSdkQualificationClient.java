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
 * Time: 14:02
 */
@FeignClient(name = "BOSdkQualificationClient", url = "${feign.backoffice-public-services.base-url}/v1/sdk-qualification")
public interface BOSdkQualificationClient {

    @GetMapping("/autocomplete")
    List<BaseAutocompleteDTO> autocompleteSdkQualification(@RequestParam String name,
                                                                @RequestParam(required = false) Integer page,
                                                                @RequestParam(required = false) Integer pageSize);
}
