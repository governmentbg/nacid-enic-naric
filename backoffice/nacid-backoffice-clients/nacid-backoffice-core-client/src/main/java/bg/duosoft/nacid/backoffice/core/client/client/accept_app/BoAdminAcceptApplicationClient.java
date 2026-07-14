package bg.duosoft.nacid.backoffice.core.client.client.accept_app;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "BoAdminAcceptApplicationClient", url = "${feign.backoffice-core.base-url}/v1/applications/acceptance", configuration = ClientTokenFeignConfig.class)
public interface BoAdminAcceptApplicationClient {

    @PostMapping("/files-processing")
    List<AttachedDocDTO> processFiles(@RequestBody ApplicationDTO application);

    @PostMapping("/files-processing/doc-delivery-attachment/{foFileNameAndId}")
    AttachmentDTO processDocDeliveryAttachment(@PathVariable String foFileNameAndId, @RequestBody ApplicationDTO application);

}
