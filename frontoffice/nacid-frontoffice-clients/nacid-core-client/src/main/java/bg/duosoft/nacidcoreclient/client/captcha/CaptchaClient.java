package bg.duosoft.nacidcoreclient.client.captcha;

import bg.duosoft.nacidcoreclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CaptchaClient", url = "${feign.core-api.base-url}/v1/captcha", configuration = SecContextFeignConfig.class)
public interface CaptchaClient extends BaseCaptchaClient {

}
