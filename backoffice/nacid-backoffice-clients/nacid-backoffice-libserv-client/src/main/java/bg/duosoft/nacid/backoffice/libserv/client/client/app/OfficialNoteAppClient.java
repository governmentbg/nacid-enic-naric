package bg.duosoft.nacid.backoffice.libserv.client.client.app;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.official_note.OfficialNoteAppDTO;
import bg.duosoft.nacid.backoffice.libserv.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "OfficialNoteAppClient", url = "${feign.backoffice-libserv.base-url}/v1/official-notes", configuration = SecContextFeignConfig.class)
public interface OfficialNoteAppClient {

    @GetMapping(value = "/{id}")
    OfficialNoteAppDTO selectById(@PathVariable Integer id);
}
