package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.inquiry.InquiryApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.02.2023
 * Time: 11:27
 */
@FeignClient(name = "ServicesInquiryClient", url = "${feign.nacid-services-be.base-url}/v1/inquiry", configuration = SecContextFeignConfig.class)
public interface ServicesInquiryClient extends ServicesBaseApplicationClient<InquiryApplicationDTO> {
}
