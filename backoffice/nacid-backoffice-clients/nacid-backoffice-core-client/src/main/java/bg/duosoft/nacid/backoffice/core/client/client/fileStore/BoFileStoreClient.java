package bg.duosoft.nacid.backoffice.core.client.client.fileStore;


import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.06.2022
 * Time: 18:03
 */
@FeignClient(name = "BoFileStoreClient", url = "${feign.backoffice-core.base-url}/v1/file-store", configuration = SecContextFeignConfig.class)
public interface BoFileStoreClient extends BoBaseFileStoreClient {

}
