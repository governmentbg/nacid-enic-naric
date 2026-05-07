package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.unichecks.UniChecksApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.01.2023
 * Time: 16:59
 */
@FeignClient(name = "ServicesUniChecksClient", url = "${feign.nacid-services-be.base-url}/v1/uni-checks", configuration = SecContextFeignConfig.class)
public interface ServicesUniChecksClient extends ServicesBaseApplicationClient<UniChecksApplicationDTO> {
}
