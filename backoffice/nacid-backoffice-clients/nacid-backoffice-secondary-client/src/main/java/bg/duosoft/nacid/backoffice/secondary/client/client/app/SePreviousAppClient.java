package bg.duosoft.nacid.backoffice.secondary.client.client.app;

import bg.duosoft.nacid.backoffice.secondary.client.config.ClientTokenFeignConfig;
import bg.duosoft.nacidshareddto.PreviousSubmittedAppFilterDTO;
import bg.duosoft.nacidshareddto.PreviousSubmittedAppResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SePreviousAppClient", url = "${feign.backoffice-secondary-education.base-url}/v1/previous-applications", configuration = ClientTokenFeignConfig.class)
public interface SePreviousAppClient {
    @PostMapping("/filter-previous-submitted")
    PreviousSubmittedAppResultDTO filterPreviousSubmitted(@RequestBody PreviousSubmittedAppFilterDTO filter);
}
