package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 30.01.2023
 * Time: 15:09
 */
@FeignClient(name = "ServicesRegprofClient", url = "${feign.nacid-services-be.base-url}/v1/regprof", configuration = SecContextFeignConfig.class)
public interface ServicesRegprofClient extends ServicesBaseApplicationClient<RegprofApplicationDTO>{
}
