package bg.duosoft.nacid.backoffice.libserv.client.client.app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.doc_delivery.DocumentDeliveryAppDTO;
import bg.duosoft.nacid.backoffice.libserv.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DocumentDeliveryAppClient", url = "${feign.backoffice-libserv.base-url}/v1/doc-deliveries", configuration = SecContextFeignConfig.class)
public interface DocumentDeliveryAppClient {

    @GetMapping(value = "/{id}")
    DocumentDeliveryAppDTO selectById(@PathVariable Integer id);
}
