package bg.duosoft.nacidbackofficepublicservicesclient.client;

import bg.duosoft.nacidbackofficepublicservicesclient.config.ClientTokenFeignConfig;
import bg.duosoft.nacidfrontofficedto.services.common.application.DocumentRegistrationRequestDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.DocumentRegistrationResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "BODocumentRegistrationClient", url = "${feign.backoffice-public-services.base-url}/v1/abdocs-registration", configuration = ClientTokenFeignConfig.class)
public interface BOAdminDocumentRegistrationClient {

    @PostMapping
    DocumentRegistrationResultDTO registerDocument(@RequestBody DocumentRegistrationRequestDTO registrationRequest);

    @DeleteMapping("/{docId}")
    void deleteRegisteredDocument(@PathVariable("docId") Integer docId);
}
