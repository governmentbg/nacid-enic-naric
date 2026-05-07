package bg.duosoft.nacid.backoffice.core.client.client.common.applicationCertificates;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ApplicationCertificatesClient", url = "${feign.backoffice-core.base-url}/v1/application-certificates", configuration = SecContextFeignConfig.class)
public interface ApplicationCertificatesClient extends ApplicationCertificatesBaseClient {


}
