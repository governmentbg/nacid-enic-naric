package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.qualification;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.02.2023
 * Time: 16:30
 */
@FeignClient(name = "AdminQualificationClient", url = "${feign.backoffice-core.base-url}/v1/qualifications", configuration = ClientTokenFeignConfig.class)
public interface AdminQualificationClient extends BaseQualificationClient{
}
