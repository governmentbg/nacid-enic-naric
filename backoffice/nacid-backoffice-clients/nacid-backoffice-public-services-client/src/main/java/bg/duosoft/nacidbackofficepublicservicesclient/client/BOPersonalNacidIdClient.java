package bg.duosoft.nacidbackofficepublicservicesclient.client;

import bg.duosoft.nacidfrontofficedto.person.PersonalNacidIdentifierDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "BOPersonalNacidIdClient", url = "${feign.backoffice-public-services.base-url}/v1/personal-nacid-id")
public interface BOPersonalNacidIdClient {

    @PostMapping
    PersonalNacidIdentifierDTO generate(@RequestParam("userGenerated") String userGenerated);

}
