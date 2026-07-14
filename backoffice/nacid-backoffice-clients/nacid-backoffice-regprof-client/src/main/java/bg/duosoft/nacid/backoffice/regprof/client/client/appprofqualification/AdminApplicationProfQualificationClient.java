package bg.duosoft.nacid.backoffice.regprof.client.client.appprofqualification;

import bg.duosoft.nacid.backoffice.regprof.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 17.05.2023
 * Time: 17:28
 */
@FeignClient(name = "AdminApplicationProfQualificationClient", url = "${feign.backoffice-regprof.base-url}/v1/application-prof-qualifications", configuration = ClientTokenFeignConfig.class)
public interface AdminApplicationProfQualificationClient extends ApplicationProfQualificationBaseClient {
}
