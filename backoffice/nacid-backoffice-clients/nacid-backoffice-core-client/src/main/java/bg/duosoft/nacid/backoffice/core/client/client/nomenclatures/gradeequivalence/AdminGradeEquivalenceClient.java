package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.gradeequivalence;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminGradeEquivalenceClient", url = "${feign.backoffice-core.base-url}/v1/grade-equivalences", configuration = ClientTokenFeignConfig.class)
public interface AdminGradeEquivalenceClient extends BaseGradeEquivalenceClient {
}
