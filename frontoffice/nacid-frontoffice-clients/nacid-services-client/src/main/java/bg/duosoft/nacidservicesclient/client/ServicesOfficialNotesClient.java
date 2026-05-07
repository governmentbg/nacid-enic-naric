package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.officialnotes.OfficialNotesApplicationDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 28.02.2023
 * Time: 11:27
 */
@FeignClient(name = "ServicesOfficialNotesClient", url = "${feign.nacid-services-be.base-url}/v1/official-notes", configuration = SecContextFeignConfig.class)
public interface ServicesOfficialNotesClient extends ServicesBaseApplicationClient<OfficialNotesApplicationDTO> {
}
