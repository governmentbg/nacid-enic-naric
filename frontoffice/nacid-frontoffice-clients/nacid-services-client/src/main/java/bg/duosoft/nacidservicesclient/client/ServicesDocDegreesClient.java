package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.docdegrees.DocDegreesApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 30.01.2023
 * Time: 15:09
 */
@FeignClient(name = "ServicesDocDegreesClient", url = "${feign.nacid-services-be.base-url}/v1/doc-degrees", configuration = SecContextFeignConfig.class)
public interface ServicesDocDegreesClient extends ServicesBaseApplicationClient<DocDegreesApplicationDTO> {
}
