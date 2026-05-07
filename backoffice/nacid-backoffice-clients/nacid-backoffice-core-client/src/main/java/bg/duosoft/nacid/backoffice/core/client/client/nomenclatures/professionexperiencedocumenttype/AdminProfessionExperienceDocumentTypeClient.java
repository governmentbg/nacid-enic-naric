package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.professionexperiencedocumenttype;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminProfessionExperienceDocumentTypeClient", url = "${feign.backoffice-core.base-url}/v1/profession-experience-document-type", configuration = ClientTokenFeignConfig.class)
public interface AdminProfessionExperienceDocumentTypeClient extends BaseProfessionExperienceDocumentTypeClient {
}
