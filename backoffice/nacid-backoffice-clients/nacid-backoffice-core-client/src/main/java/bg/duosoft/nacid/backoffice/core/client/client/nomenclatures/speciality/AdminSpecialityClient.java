package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.speciality;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.02.2023
 * Time: 13:14
 */
@FeignClient(name = "AdminSpecialityClient", url = "${feign.backoffice-core.base-url}/v1/speciality", configuration = ClientTokenFeignConfig.class)
public interface AdminSpecialityClient extends BaseSpecialityClient {
}
