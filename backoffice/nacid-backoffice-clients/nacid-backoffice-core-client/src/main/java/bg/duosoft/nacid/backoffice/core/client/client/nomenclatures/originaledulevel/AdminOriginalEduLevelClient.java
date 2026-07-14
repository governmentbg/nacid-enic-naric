package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.originaledulevel;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 18:16
 */
@FeignClient(name = "AdminOriginalEduLevelClient", url = "${feign.backoffice-core.base-url}/v1/original-edu-levels", configuration = ClientTokenFeignConfig.class)
public interface AdminOriginalEduLevelClient extends BaseOriginalEduLevelClient {
}
