package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.graduationdocumenttype;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminGraduationDocumentTypeClient", url = "${feign.backoffice-core.base-url}/v1/graduation-document-type", configuration = ClientTokenFeignConfig.class)
public interface AdminGraduationDocumentTypeClient extends BaseGraduationDocumentTypeClient {
}
