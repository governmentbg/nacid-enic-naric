package bg.duosoft.nacid.backoffice.core.client.client.common.applicationCertificates;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminApplicationCertificatesClient", url = "${feign.backoffice-core.base-url}/v1/application-certificates", configuration = ClientTokenFeignConfig.class)
public interface AdminApplicationCertificatesClient extends ApplicationCertificatesBaseClient {
}
