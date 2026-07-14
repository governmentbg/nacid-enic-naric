package bg.duosoft.nacidcoreclient.client.countries;

import bg.duosoft.nacidcoreclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CountriesClient", url = "${feign.core-api.base-url}/v1/countries", configuration = SecContextFeignConfig.class)
public interface CountriesClient extends BaseCountriesClient {

}
