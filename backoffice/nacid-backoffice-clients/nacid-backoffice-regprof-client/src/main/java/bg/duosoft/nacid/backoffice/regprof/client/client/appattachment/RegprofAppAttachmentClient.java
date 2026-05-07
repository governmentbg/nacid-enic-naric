package bg.duosoft.nacid.backoffice.regprof.client.client.appattachment;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.regprof.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "RegprofAppAttachmentClient", url = "${feign.backoffice-regprof.base-url}/v1/applications/attachments", configuration = SecContextFeignConfig.class)
public interface RegprofAppAttachmentClient {
    @PostMapping(value = "save/{applicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void saveAttachment(@PathVariable Integer applicationId, @RequestBody AttachedDocDTO attachment);
}
