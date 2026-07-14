package bg.duosoft.nacid.backoffice.rudi.client.client.app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationDTO;
import bg.duosoft.nacid.backoffice.rudi.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RudiAppClient", url = "${feign.backoffice-rudi.base-url}/v1/applications", configuration = SecContextFeignConfig.class)
public interface RudiAppClient {

    @GetMapping(value = "/{id}")
    RudiApplicationDTO selectById(@PathVariable Integer id);
}
