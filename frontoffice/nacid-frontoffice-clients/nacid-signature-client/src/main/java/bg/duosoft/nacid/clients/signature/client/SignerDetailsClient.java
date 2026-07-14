package bg.duosoft.nacid.clients.signature.client;

import bg.duosoft.nacid.clients.signature.config.SecContextFeignConfig;
import bg.duosoft.nacid.clients.signature.model.SignerDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "SignerDetailsClient", url = "${feign.signature-api.base-url}", path = "/v1/signers", configuration = SecContextFeignConfig.class)
public interface SignerDetailsClient {

    @GetMapping(value = "/{id}")
    List<SignerDetails> getSignerDetails(@PathVariable String id);
}
