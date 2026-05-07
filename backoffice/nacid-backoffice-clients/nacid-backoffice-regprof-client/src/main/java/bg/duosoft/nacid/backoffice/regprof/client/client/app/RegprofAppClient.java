package bg.duosoft.nacid.backoffice.regprof.client.client.app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.RegprofApplicationDTO;
import bg.duosoft.nacid.backoffice.regprof.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RegprofAppClient", url = "${feign.backoffice-regprof.base-url}/v1/applications", configuration = SecContextFeignConfig.class)
public interface RegprofAppClient {

    @GetMapping(value = "/{id}")
    RegprofApplicationDTO selectById(@PathVariable Integer id);
}
