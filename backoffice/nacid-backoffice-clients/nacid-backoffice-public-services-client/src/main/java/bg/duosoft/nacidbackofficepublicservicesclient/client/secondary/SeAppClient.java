package bg.duosoft.nacidbackofficepublicservicesclient.client.secondary;

import bg.duosoft.nacidshareddto.PreviousSubmittedAppFilterDTO;
import bg.duosoft.nacidshareddto.PreviousSubmittedAppResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.02.2023
 * Time: 13:19
 */
@FeignClient(name = "SeAppClient", url = "${feign.backoffice-public-services.base-url}/v1/secondary")
public interface SeAppClient {
    @PostMapping("/filter-previous-submitted")
    PreviousSubmittedAppResultDTO filterPreviousSubmitted(@RequestBody PreviousSubmittedAppFilterDTO filter);
}
