package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.countries;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CountriesClient", url = "${feign.backoffice-core.base-url}/v1/countries", configuration = {SecContextFeignConfig.class})
public interface CountriesClient extends BaseCountriesClient {

}
