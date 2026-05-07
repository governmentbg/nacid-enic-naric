package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.commissionmemberposition;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminCommissionMemberPositionClient", url = "${feign.backoffice-core.base-url}/v1/commission-member-position", configuration = ClientTokenFeignConfig.class)
public interface AdminCommissionMemberPositionClient extends BaseCommissionMemberPositionClient {
}
