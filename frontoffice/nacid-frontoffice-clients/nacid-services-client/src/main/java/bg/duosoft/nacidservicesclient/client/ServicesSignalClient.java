package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.signal.SignalApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.01.2023
 * Time: 16:59
 */
@FeignClient(name = "ServicesSignalClient", url = "${feign.nacid-services-be.base-url}/v1/signal", configuration = SecContextFeignConfig.class)
public interface ServicesSignalClient extends ServicesBaseApplicationClient<SignalApplicationDTO> {
}
