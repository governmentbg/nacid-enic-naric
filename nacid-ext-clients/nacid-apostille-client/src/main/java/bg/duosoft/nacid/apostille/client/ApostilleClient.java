package bg.duosoft.nacid.apostille.client;

import bg.duosoft.nacid.apostille.dto.ApostilleApplication;
import bg.duosoft.nacid.apostille.dto.RegisterApostilleApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * User: ggeorgiev
 * Date: 05.07.2023
 * Time: 17:03
 */
@FeignClient(name = "ApostilleClient", url = "${feign.apostille.base-url}")
public interface ApostilleClient {
    @PostMapping("/Public/AddCertificate")
    public RegisterApostilleApplicationResponse registerApplication(ApostilleApplication request);
}
