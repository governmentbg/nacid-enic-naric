package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.apptype;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminApplicationTypeClient", url = "${feign.backoffice-core.base-url}/v1/application-types", configuration = ClientTokenFeignConfig.class)
public interface AdminApplicationTypeClient extends BaseNomenclaturesClient<String, ApplicationTypeDTO> {
}
