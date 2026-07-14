package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.secondaryprofgroup;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SecondaryProfessionGroupClient", url = "${feign.backoffice-core.base-url}/v1/secondary-profession-groups", configuration = {SecContextFeignConfig.class})
public interface SecondaryProfessionGroupClient extends BaseSecondaryProfessionGroupClient {

}
