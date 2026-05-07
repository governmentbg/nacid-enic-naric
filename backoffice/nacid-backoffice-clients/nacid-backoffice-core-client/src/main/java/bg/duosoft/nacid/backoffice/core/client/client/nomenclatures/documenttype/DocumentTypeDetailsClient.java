package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.documenttype;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "DocumentTypeDetailsClient", url = "${feign.backoffice-core.base-url}/v1/document-type-details", configuration = {SecContextFeignConfig.class})
public interface DocumentTypeDetailsClient {
    @GetMapping("/by-limit-params")
    List<DocumentTypeDetailDTO> selectDocumentTypeDetails(@RequestParam(value = "applicationId") Integer applicationId,
                                                          @RequestParam(value = "docCategory") String docCategory, @RequestParam(value = "docType") Integer docType);
}
