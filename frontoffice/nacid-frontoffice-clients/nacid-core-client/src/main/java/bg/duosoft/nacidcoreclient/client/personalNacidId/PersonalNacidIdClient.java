package bg.duosoft.nacidcoreclient.client.personalNacidId;

import bg.duosoft.nacidcoreclient.config.ClientTokenFeignConfig;
import bg.duosoft.nacidfrontofficedto.person.PersonalNacidIdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PersonalNacidIdClient", url = "${feign.core-api.base-url}/v1/personal-nacid-id", configuration = ClientTokenFeignConfig.class)
public interface PersonalNacidIdClient {

    @GetMapping("/for-username/{username}")
    PersonalNacidIdDTO getPersonalNacidIdForUsername(@PathVariable String username);

    @GetMapping("/for-value/{value}")
    PersonalNacidIdDTO getPersonalNacidIdForValue(@PathVariable Integer value);

    @PostMapping
    PersonalNacidIdDTO generateAndSave(@RequestParam String username);

    @DeleteMapping("/{value}")
    void delete(@PathVariable("value") Integer value);

}
