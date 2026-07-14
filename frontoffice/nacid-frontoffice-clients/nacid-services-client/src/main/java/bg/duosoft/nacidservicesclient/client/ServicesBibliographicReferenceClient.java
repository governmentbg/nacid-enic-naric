package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.biblioreference.BiblioReferenceApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.03.2023
 * Time: 17:23
 */
@FeignClient(name = "ServicesBibliographicReferenceClient", url = "${feign.nacid-services-be.base-url}/v1/bibliographic-reference", configuration = SecContextFeignConfig.class)
public interface ServicesBibliographicReferenceClient extends ServicesBaseApplicationClient<BiblioReferenceApplicationDTO> {
}
