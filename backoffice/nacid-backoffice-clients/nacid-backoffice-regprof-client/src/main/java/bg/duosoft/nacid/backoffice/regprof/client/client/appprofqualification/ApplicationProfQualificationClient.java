package bg.duosoft.nacid.backoffice.regprof.client.client.appprofqualification;

import bg.duosoft.nacid.backoffice.regprof.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 17.05.2023
 * Time: 17:28
 */
@FeignClient(name = "ApplicationProfQualificationClient", url = "${feign.backoffice-regprof.base-url}/v1/application-prof-qualifications", configuration = SecContextFeignConfig.class)
public interface ApplicationProfQualificationClient extends ApplicationProfQualificationBaseClient {
}
