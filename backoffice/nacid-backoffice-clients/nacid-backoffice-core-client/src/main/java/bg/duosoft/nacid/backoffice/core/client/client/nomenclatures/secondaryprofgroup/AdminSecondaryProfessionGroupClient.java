package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.secondaryprofgroup;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminSecondaryProfessionGroupClient", url = "${feign.backoffice-core.base-url}/v1/secondary-profession-groups", configuration = ClientTokenFeignConfig.class)
public interface AdminSecondaryProfessionGroupClient extends BaseSecondaryProfessionGroupClient {
}
