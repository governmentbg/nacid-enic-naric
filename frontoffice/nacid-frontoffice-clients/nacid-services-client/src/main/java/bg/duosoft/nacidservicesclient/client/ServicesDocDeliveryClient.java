package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.docdelivery.DocDeliveryApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 06.03.2023
 * Time: 16:17
 */
@FeignClient(name = "ServicesDocDeliveryClient", url = "${feign.nacid-services-be.base-url}/v1/document-delivery", configuration = SecContextFeignConfig.class)
public interface ServicesDocDeliveryClient extends ServicesBaseApplicationClient<DocDeliveryApplicationDTO> {
}
