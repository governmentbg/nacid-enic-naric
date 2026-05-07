package bg.duosoft.nacidcoreclient.client.cfgdoctyperequirement;

import bg.duosoft.nacidcoreclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.01.2023
 * Time: 18:41
 */
@FeignClient(name = "CfgDocTypeRequirementClient", url = "${feign.core-api.base-url}/v1/cfg-doc-type-requirement", configuration = SecContextFeignConfig.class)
public interface CfgDocTypeRequirementClient extends BaseCfgDocTypeRequirementClient {
}
