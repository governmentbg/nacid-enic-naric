package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationNoteDTO;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ServicesApplicationNoteClient", url = "${feign.nacid-services-be.base-url}/v1/app-notes", configuration = SecContextFeignConfig.class)
public interface ServicesApplicationNoteClient {

    @GetMapping("/app/{id}")
    List<ApplicationNoteDTO> selectAllByApplication(@PathVariable Integer id);

}
