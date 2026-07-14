package bg.duosoft.nacid.clients.signature.client;


import bg.duosoft.nacid.clients.signature.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FileDownloadClient", url = "${feign.signature-api.base-url}", path = "/v1/file-download", configuration = SecContextFeignConfig.class)
public interface FileDownloadClient {
    @GetMapping(value = "/customer-signed")
    ResponseEntity<byte[]> downloadFileWithCustomerSignature(@RequestParam String id);
}
