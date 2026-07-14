package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.qualification;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.02.2023
 * Time: 16:30
 */
@FeignClient(name = "QualificationClient", url = "${feign.backoffice-core.base-url}/v1/qualifications", configuration = {SecContextFeignConfig.class})
public interface QualificationClient extends BaseQualificationClient {
}
