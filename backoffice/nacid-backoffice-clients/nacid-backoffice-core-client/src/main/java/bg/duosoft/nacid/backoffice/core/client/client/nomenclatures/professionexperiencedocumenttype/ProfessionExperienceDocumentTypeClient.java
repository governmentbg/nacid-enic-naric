package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.professionexperiencedocumenttype;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ProfessionExperienceDocumentTypeClient", url = "${feign.backoffice-core.base-url}/v1/profession-experience-document-type", configuration = {SecContextFeignConfig.class})
public interface ProfessionExperienceDocumentTypeClient extends BaseProfessionExperienceDocumentTypeClient {

}
