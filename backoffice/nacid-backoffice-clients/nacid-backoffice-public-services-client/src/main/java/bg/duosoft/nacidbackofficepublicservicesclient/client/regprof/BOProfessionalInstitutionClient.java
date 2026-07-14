package bg.duosoft.nacidbackofficepublicservicesclient.client.regprof;

import bg.duosoft.nacidfrontofficedto.autocomplete.BaseAutocompleteDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.EducationType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.04.2023
 * Time: 15:48
 */
@FeignClient(name = "BOProfessionalInstitutionClient", url = "${feign.backoffice-public-services.base-url}/v1/regprof/prof-institution")
public interface BOProfessionalInstitutionClient {

    @GetMapping("/autocomplete")
    List<BaseAutocompleteDTO> selectForAutocomplete(@RequestParam EducationType educationType,
                                                         @RequestParam String name,
                                                         @RequestParam(required = false, defaultValue = "true") Boolean onlyActive,
                                                         @RequestParam(required = false) Integer page,
                                                         @RequestParam(required = false) Integer pageSize);

    @GetMapping("/former-name-autocomplete")
    List<BaseAutocompleteDTO> selectFormerNamesForAutocomplete(@RequestParam Integer profInstitutionId,
                                                                    @RequestParam String formerName,
                                                                    @RequestParam(required = false, defaultValue = "true") Boolean onlyActive,
                                                                    @RequestParam(required = false) Integer page,
                                                                    @RequestParam(required = false) Integer pageSize);

}
