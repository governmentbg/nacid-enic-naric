package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.originaledulevel;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 01.06.2023
 * Time: 18:15
 */
@FeignClient(name = "OriginalEduLevelClient", url = "${feign.backoffice-core.base-url}/v1/original-edu-levels", configuration = {SecContextFeignConfig.class})
public interface OriginalEduLevelClient extends BaseOriginalEduLevelClient{
}
