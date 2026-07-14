package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.additionaldoc.AdditionalDocApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ServicesRudiAdditionalDocumentsClient", url = "${feign.nacid-services-be.base-url}/v1/rudi-additional-documents", configuration = SecContextFeignConfig.class)
public interface ServicesRudiAdditionalDocumentsClient extends ServicesBaseApplicationClient<AdditionalDocApplicationDTO> {

}
