package bg.duosoft.nacid.backoffice.rudi.client.client.commissionmember;

import bg.duosoft.nacid.backoffice.rudi.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:04
 */
@FeignClient(name = "CommissionMemberClient", url = "${feign.backoffice-rudi.base-url}/v1/commission-members", configuration = SecContextFeignConfig.class)
public interface CommissionMemberClient extends CommissionMemberBaseClient {
}
