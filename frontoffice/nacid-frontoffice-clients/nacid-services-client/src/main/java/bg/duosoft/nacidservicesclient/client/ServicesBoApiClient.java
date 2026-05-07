package bg.duosoft.nacidservicesclient.client;

import bg.duosoft.nacidfrontofficedto.Page;
import bg.duosoft.nacidfrontofficedto.services.common.application.*;
import bg.duosoft.nacidservicesclient.config.SecContextFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 14:53
 */
@FeignClient(name = "ServicesBoApiClient", url = "${feign.nacid-services-be.base-url}/v1/bo-api", configuration = SecContextFeignConfig.class)
public interface ServicesBoApiClient {

    @PostMapping("/filter-applications")
    Page<ApplicationListRecordDTO> filterApplications(@RequestBody ApplicationListFilterDTO listFilter);

    @GetMapping("/get-application-by-id")
    ApplicationListRecordDTO getApplicationById(@RequestParam Integer id);

    @PostMapping("/accept-application")
    void acceptApplication(@RequestBody AcceptApplicationRequestDTO acceptApplicationRequest);

    @PostMapping("/accept-dms-application")
    byte[] acceptDmsApplication(@RequestBody AcceptApplicationRequestDTO acceptApplicationRequest);

    @PostMapping("/accept-regprof-apostille-application")
    void acceptRegprofApostilleApplication(@RequestBody AcceptApplicationRequestDTO acceptApplicationRequest);

    @PostMapping("/revert-to-draft")
    void revertApplicationToDraft(@RequestBody RevertApplicationStatusToDraftRequestDTO revertRequest);

    @PostMapping("/simply-change-application-status")
    void simplyChangeApplicationStatus(@RequestBody ChangeFoApplicationStatusRequestDTO changeStatusRequest);

    @GetMapping("/get-related-apps-from-multiple")
    List<ApplicationMultipleRecordDTO> getRelatedAppsFromMultiple(@RequestParam Integer singleApplicationId);

    @GetMapping("/get-accepted-receipt")
    byte[] getAcceptedReceiptContent(@RequestParam Integer id);
    @PostMapping("/original-documents-waiting")
    ResponseEntity.BodyBuilder setOriginalDocumentsWaiting(@RequestParam Integer id, @RequestParam boolean waiting);

    @GetMapping("/original-documents-waiting")
    Boolean getOriginalDocumentsWaiting(@RequestParam Integer id);

}
