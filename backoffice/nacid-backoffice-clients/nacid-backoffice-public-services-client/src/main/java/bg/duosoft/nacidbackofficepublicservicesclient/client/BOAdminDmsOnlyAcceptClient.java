package bg.duosoft.nacidbackofficepublicservicesclient.client;

import bg.duosoft.nacidbackofficepublicservicesclient.config.ClientTokenFeignConfig;
import bg.duosoft.nacidfrontofficedto.services.common.application.DmsApplicationInsertRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.08.2023
 * Time: 19:09
 */
@FeignClient(name = "BOAdminDmsOnlyAcceptClient", url = "${feign.backoffice-public-services.base-url}/v1/libserv/accept/dms-only", configuration = ClientTokenFeignConfig.class)
public interface BOAdminDmsOnlyAcceptClient {

    @PostMapping
    void acceptLibservInDmsOnly(@RequestBody DmsApplicationInsertRequestDTO dmsApplicationInsertRequest);
}
