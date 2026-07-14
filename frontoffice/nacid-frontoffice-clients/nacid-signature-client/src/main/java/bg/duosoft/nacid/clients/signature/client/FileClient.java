package bg.duosoft.nacid.clients.signature.client;

import bg.duosoft.nacid.clients.signature.config.SecContextFeignConfig;
import bg.duosoft.nacid.clients.signature.model.FileInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "FileClient", url = "${feign.signature-api.base-url}", path = "/v1/file", configuration = SecContextFeignConfig.class)
public interface FileClient {
    @GetMapping("/status/{id}")
    String getFileStatus(@PathVariable String id);
    @GetMapping("/info/{id}")
    FileInfo getFileInfo(@PathVariable String id);
}
