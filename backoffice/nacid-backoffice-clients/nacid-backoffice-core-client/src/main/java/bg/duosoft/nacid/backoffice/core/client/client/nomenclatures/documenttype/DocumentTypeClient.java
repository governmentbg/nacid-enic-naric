package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.documenttype;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "DocumentTypeClient", url = "${feign.backoffice-core.base-url}/v1/document-types", configuration = {SecContextFeignConfig.class})
public interface DocumentTypeClient extends BaseDocumentTypeClient {

}
