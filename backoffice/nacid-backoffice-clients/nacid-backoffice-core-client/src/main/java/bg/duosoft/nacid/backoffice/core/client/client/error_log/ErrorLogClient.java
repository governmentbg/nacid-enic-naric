package bg.duosoft.nacid.backoffice.core.client.client.error_log;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ErrorLogClient", url = "${feign.backoffice-core.base-url}/v1/error-logs", configuration = SecContextFeignConfig.class)
public interface ErrorLogClient extends BaseErrorLogClient {
}
