package bg.duosoft.nacid.backoffice.regprof.client.client.professionname;

import bg.duosoft.nacid.backoffice.regprof.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:04
 */
@FeignClient(name = "ProfessionNameClient", url = "${feign.backoffice-regprof.base-url}/v1/profession-names", configuration = SecContextFeignConfig.class)
public interface ProfessionNameClient extends ProfessionNameBaseClient {
}
