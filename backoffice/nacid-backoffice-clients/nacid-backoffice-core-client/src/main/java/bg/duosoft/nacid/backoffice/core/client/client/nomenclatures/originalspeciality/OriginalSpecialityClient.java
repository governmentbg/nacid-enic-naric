package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.originalspeciality;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 18:15
 */
@FeignClient(name = "OriginalSpecialityClient", url = "${feign.backoffice-core.base-url}/v1/original-speciality", configuration = {SecContextFeignConfig.class})
public interface OriginalSpecialityClient extends BaseOriginalSpecialityClient {
}
