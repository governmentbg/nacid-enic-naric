package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.speciality;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.02.2023
 * Time: 13:14
 */
@FeignClient(name = "SpecialityClient", url = "${feign.backoffice-core.base-url}/v1/speciality", configuration = {SecContextFeignConfig.class})
public interface SpecialityClient extends BaseSpecialityClient {
}
