package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.documenttype;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: ggeorgiev
 * Date: 19.08.2022
 * Time: 13:41
 */
@FeignClient(name = "AdminDocumentTypeClient", url = "${feign.backoffice-core.base-url}/v1/document-types", configuration = ClientTokenFeignConfig.class)
public interface AdminDocumentTypeClient extends BaseDocumentTypeClient {
}
