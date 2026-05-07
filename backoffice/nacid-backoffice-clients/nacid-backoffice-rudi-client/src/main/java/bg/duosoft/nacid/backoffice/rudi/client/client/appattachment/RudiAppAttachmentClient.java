package bg.duosoft.nacid.backoffice.rudi.client.client.appattachment;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.rudi.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RudiAppAttachmentClient", url = "${feign.backoffice-rudi.base-url}/v1/applications/attachments", configuration = SecContextFeignConfig.class)
public interface RudiAppAttachmentClient {
    @PostMapping(value = "save/{applicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void saveAttachment(@PathVariable Integer applicationId, @RequestBody AttachedDocDTO attachment);
}
