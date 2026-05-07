package bg.duosoft.nacidbackofficepublicservicesclient.client;

import bg.duosoft.nacidbackofficepublicservicesclient.config.ClientTokenFeignConfig;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCertificateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 05.10.2023
 * Time: 16:01
 */
@FeignClient(name = "BOAdminApplicationCertificateClient", url = "${feign.backoffice-public-services.base-url}/v1/application-certificate", configuration = ClientTokenFeignConfig.class)
public interface BOAdminApplicationCertificateClient {

    @GetMapping("/{applicationId}")
    ApplicationCertificateDTO getCertificateForFoApplication(@PathVariable Integer applicationId);

    @GetMapping(value = "/exists/by-app-type")
    Boolean hasAppCertificateByCertNumberAndAppType(@RequestParam("certNumber") String certNumber, @RequestParam("appType") String appType);
}
