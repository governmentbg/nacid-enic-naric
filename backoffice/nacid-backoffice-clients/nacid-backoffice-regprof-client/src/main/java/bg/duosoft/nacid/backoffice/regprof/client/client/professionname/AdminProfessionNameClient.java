package bg.duosoft.nacid.backoffice.regprof.client.client.professionname;

import bg.duosoft.nacid.backoffice.regprof.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminProfessionNameClient", url = "${feign.backoffice-regprof.base-url}/v1/profession-names", configuration = ClientTokenFeignConfig.class)
public interface AdminProfessionNameClient extends ProfessionNameBaseClient {
}
