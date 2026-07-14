package bg.duosoft.nacid.backoffice.libserv.client.client.abdocs;

import bg.duosoft.nacid.backoffice.libserv.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "abdocsMissingLibservDocumentClient", url = "${feign.backoffice-libserv.base-url}/v1/abdocs/missing-doc", configuration = ClientTokenFeignConfig.class)
public interface AbdocsMissingLibservDocumentClient {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addMissingAbdocsDocument(@RequestParam Integer applicationId);

}
