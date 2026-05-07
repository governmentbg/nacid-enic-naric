package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.duplicate.DuplicateApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ServicesRudiDuplicateClient", url = "${feign.nacid-services-be.base-url}/v1/rudi-duplicates", configuration = SecContextFeignConfig.class)
public interface ServicesRudiDuplicateClient extends ServicesBaseApplicationClient<DuplicateApplicationDTO> {

}
