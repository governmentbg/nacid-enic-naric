package bg.duosoft.nacid.backoffice.rudi.client.client.commissionmember;

import bg.duosoft.nacid.backoffice.rudi.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminCommissionMemberClient", url = "${feign.backoffice-rudi.base-url}/v1/commission-members", configuration = ClientTokenFeignConfig.class)
public interface AdminCommissionMemberClient extends CommissionMemberBaseClient {
}
