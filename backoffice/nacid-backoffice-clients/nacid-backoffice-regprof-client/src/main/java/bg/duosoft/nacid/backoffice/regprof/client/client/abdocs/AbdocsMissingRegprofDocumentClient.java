package bg.duosoft.nacid.backoffice.regprof.client.client.abdocs;

import bg.duosoft.nacid.backoffice.regprof.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "abdocsMissingRegprofDocumentClient", url = "${feign.backoffice-regprof.base-url}/v1/abdocs/missing-doc", configuration = ClientTokenFeignConfig.class)
public interface AbdocsMissingRegprofDocumentClient {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addMissingAbdocsDocument(@RequestParam Integer applicationId);

}
