package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.documentreceivemethod;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:04
 */
@FeignClient(name = "DocumentReceiveMethodClient", url = "${feign.backoffice-core.base-url}/v1/document-receive-methods", configuration = ClientTokenFeignConfig.class)
public interface DocumentReceiveMethodClient extends BaseNomenclaturesClient<String, DocumentReceiveMethodDTO> {
}
