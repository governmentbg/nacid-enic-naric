package bg.duosoft.nacid.clients.signature.client;

import bg.duosoft.nacid.clients.signature.config.SecContextFeignConfig;
import bg.duosoft.nacid.clients.signature.model.SignFileRequest;
import bg.duosoft.nacid.clients.signature.model.SignFileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PdfSignerClient", url = "${feign.signature-api.base-url}", path = "/v1/pdf-signature", configuration = SecContextFeignConfig.class)
public interface PdfSignerClient {

    @PostMapping("/sign")
    ResponseEntity<SignFileResponse> signPdfDocument(@RequestBody SignFileRequest fileRequest);
}
