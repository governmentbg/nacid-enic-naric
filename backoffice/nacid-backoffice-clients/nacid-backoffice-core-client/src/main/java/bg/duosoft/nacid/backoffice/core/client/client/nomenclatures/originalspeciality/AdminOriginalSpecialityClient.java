package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.originalspeciality;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 18:16
 */
@FeignClient(name = "AdminOriginalSpecialityClient", url = "${feign.backoffice-core.base-url}/v1/original-speciality", configuration = ClientTokenFeignConfig.class)
public interface AdminOriginalSpecialityClient extends BaseOriginalSpecialityClient {
}
