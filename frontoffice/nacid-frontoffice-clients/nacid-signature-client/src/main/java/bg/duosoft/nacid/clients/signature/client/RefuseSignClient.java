package bg.duosoft.nacid.clients.signature.client;

import bg.duosoft.nacid.clients.signature.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RefuseSignClient", url = "${feign.signature-api.base-url}", path = "/v1/sign-refuse", configuration = SecContextFeignConfig.class)
public interface RefuseSignClient {
    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> refuseSigning(@PathVariable String id);
}
