package bg.duosoft.nacid.opendata.client;

import bg.duosoft.nacid.opendata.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: ggeorgiev
 * Date: 08.01.2024
 * Time: 16:55
 */
@FeignClient(name = "OpendataClient", url = "${feign.opendata.base-url}")
public interface OpendataClient {

    @PostMapping("/listUsers")
    public ListUsersResponse listUsers(@RequestBody ListUsersRequest request);

    @PostMapping("/listResources")
    public ListResourcesResponse listResources(@RequestBody ListResourcesRequest request);

    @PostMapping("/editResourceMetadata")
    public EditResourceMetadataResponse editResourceMetadata(@RequestBody EditResourceMetadataRequest request);

    @PostMapping("/updateResourceData")
    public UpdateResourceDataResponse updateResourceData(@RequestBody UpdateResourceDataRequest request);


    @PostMapping("/tsv2json")
    public Tsv2JsonResponse tsv2json(@RequestBody Tsv2JsonRequest request);
}
