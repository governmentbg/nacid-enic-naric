package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.serecognition.SeRecognitionApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import bg.duosoft.nacidshareddto.PreviousSubmittedAppFilterDTO;
import bg.duosoft.nacidshareddto.PreviousSubmittedAppResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ServicesSEClient", url = "${feign.nacid-services-be.base-url}/v1/se-recognition", configuration = SecContextFeignConfig.class)
public interface ServicesSEClient extends ServicesBaseApplicationClient<SeRecognitionApplicationDTO> {
    @PostMapping("/filter-previous-submitted")
     PreviousSubmittedAppResultDTO filterPreviousSubmitted(@RequestBody PreviousSubmittedAppFilterDTO filter);
}
