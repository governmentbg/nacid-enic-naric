package bg.duosoft.nacid.backoffice.core.client.client.nomenclatures.gradeequivalence;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "GradeEquivalenceClient", url = "${feign.backoffice-core.base-url}/v1/grade-equivalences", configuration = {SecContextFeignConfig.class})
public interface GradeEquivalenceClient extends BaseGradeEquivalenceClient {

}
