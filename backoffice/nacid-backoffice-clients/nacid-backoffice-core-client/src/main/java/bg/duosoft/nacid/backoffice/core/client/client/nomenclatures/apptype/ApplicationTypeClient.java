package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.apptype;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:04
 */
@FeignClient(name = "ApplicationTypeClient", url = "${feign.backoffice-core.base-url}/v1/application-types", configuration = ClientTokenFeignConfig.class)
public interface ApplicationTypeClient extends BaseNomenclaturesClient<String, ApplicationTypeDTO> {
}
