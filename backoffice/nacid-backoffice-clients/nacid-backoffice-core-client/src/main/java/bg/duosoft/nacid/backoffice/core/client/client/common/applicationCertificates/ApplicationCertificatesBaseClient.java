package bg.duosoft.nacid.backoffice.core.client.client.common.applicationCertificates;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationCertificatesDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ApplicationCertificatesBaseClient {
    @GetMapping("/select-certificates-fo")
    ApplicationCertificatesDTO selectCertificatesForFO(@RequestParam Integer efilingId);

    @GetMapping(value = "/exists/by-app-type")
    Boolean hasAppCertificateByCertNumberAndAppType(@RequestParam("certNumber") String certNumber, @RequestParam("appType") String appType);
}
