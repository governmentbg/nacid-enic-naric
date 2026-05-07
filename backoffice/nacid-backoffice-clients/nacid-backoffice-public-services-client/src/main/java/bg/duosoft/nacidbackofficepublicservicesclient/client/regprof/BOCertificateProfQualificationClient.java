package bg.duosoft.nacidbackofficepublicservicesclient.client.regprof;

import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 17.05.2023
 * Time: 18:22
 */
@FeignClient(name = "BOCertificateProfQualificationClient", url = "${feign.backoffice-public-services.base-url}/v1/regprof/certificate-prof-qualification")
public interface BOCertificateProfQualificationClient {

    @GetMapping("/autocomplete")
    List<BaseAutocompleteDTO> autocompleteCertificateProfQualifications(@RequestParam String name,
                                                                      @RequestParam(required = false) Integer page,
                                                                      @RequestParam(required = false) Integer pageSize);
}
