package bg.duosoft.nacid.backoffice.secondary.client.client.app;

import bg.duosoft.nacid.backoffice.secondary.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SeAppClient", url = "${feign.backoffice-secondary-education.base-url}/v1/applications", configuration = SecContextFeignConfig.class)
public interface SeAppClient {

    @GetMapping(value = "/parent-cert-full-entry-number/{id}")
    String getParentCertFullEntryNumber(@PathVariable Integer id);

    @GetMapping(value = "/parent-cert-document-number/{id}")
    String getParentCertDocumentNumber(@PathVariable Integer id);
}
