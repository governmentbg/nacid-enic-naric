package bg.duosoft.nacid.backoffice.core.client.client.error_log;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminErrorLogClient", url = "${feign.backoffice-core.base-url}/v1/error-logs", configuration = ClientTokenFeignConfig.class)
public interface AdminErrorLogClient extends BaseErrorLogClient {
}
