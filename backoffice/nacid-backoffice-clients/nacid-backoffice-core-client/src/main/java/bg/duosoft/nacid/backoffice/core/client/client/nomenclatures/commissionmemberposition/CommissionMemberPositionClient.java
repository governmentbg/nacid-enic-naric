package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.commissionmemberposition;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CommissionMemberPositionClient", url = "${feign.backoffice-core.base-url}/v1/commission-member-position", configuration = {SecContextFeignConfig.class})
public interface CommissionMemberPositionClient extends BaseCommissionMemberPositionClient {

}
