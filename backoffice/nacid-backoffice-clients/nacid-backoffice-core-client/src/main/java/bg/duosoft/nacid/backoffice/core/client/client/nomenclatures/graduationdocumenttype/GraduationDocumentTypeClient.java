package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.graduationdocumenttype;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "GraduationDocumentTypeClient", url = "${feign.backoffice-core.base-url}/v1/graduation-document-type", configuration = {SecContextFeignConfig.class})
public interface GraduationDocumentTypeClient extends BaseGraduationDocumentTypeClient {

}
