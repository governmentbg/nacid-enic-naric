package bg.duosoft.nacidcoreclient.client.fileStore;

import bg.duosoft.nacidcoreclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.06.2022
 * Time: 18:03
 */
@FeignClient(name = "FileStoreClient", url = "${feign.core-api.base-url}/v1/file-store", configuration = SecContextFeignConfig.class)
public interface FileStoreClient extends BaseFileStoreClient {

}
