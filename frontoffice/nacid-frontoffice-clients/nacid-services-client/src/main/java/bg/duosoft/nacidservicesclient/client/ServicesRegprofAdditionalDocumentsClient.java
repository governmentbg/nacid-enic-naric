package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.additionaldoc.AdditionalDocApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ServicesRegprofAdditionalDocumentsClient", url = "${feign.nacid-services-be.base-url}/v1/regprof-additional-documents", configuration = SecContextFeignConfig.class)
public interface ServicesRegprofAdditionalDocumentsClient extends ServicesBaseApplicationClient<AdditionalDocApplicationDTO> {

}
