package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.cfgabdocsdocument;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminAbdocsDocumentConfigClient", url = "${feign.backoffice-core.base-url}/v1/cfg-abdocs-documents", configuration = ClientTokenFeignConfig.class)
public interface AdminCfgAbdocsDocumentClient extends BaseCfgAbdocsDocumentClient {
}
