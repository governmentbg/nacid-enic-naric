package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.documentreceivemethod;

import bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.BaseNomenclaturesClient;
import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminDocumentReceiveMethodClient", url = "${feign.backoffice-core.base-url}/v1/document-receive-methods", configuration = ClientTokenFeignConfig.class)
public interface AdminDocumentReceiveMethodClient extends BaseNomenclaturesClient<String, DocumentReceiveMethodDTO> {
}
