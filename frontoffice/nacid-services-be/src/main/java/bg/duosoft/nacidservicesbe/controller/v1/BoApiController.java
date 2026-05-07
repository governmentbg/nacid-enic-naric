package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.Page;
import bg.duosoft.nacidfrontofficedto.services.common.application.*;
import bg.duosoft.nacidservicesbe.service.*;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidservicesbe.validation.AcceptApplicationRequestValidator;
import bg.duosoft.nacidservicesbe.validation.ApplicationListFilterValidator;
import bg.duosoft.nacidservicesbe.validation.ChangeFoApplicationStatusRequestValidator;
import bg.duosoft.nacidservicesbe.validation.RevertApplicationStatusToDraftRequestValidator;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.01.2023
 * Time: 13:41
 */
@Api(tags = Tags.BO_API)
@RestController
@RequestMapping("/api/v1/bo-api")
@RequiredArgsConstructor
public class BoApiController {

    private final ServiceHelper serviceHelper;
    private final CommonApplicationService commonApplicationService;
    private final ApplicationListFilterValidator applicationListFilterValidator;
    private final AcceptApplicationRequestValidator acceptApplicationRequestValidator;
    private final ChangeFoApplicationStatusRequestValidator changeFoApplicationStatusRequestValidator;
    private final RevertApplicationStatusToDraftRequestValidator revertApplicationStatusToDraftRequestValidator;

    @PostMapping("/filter-applications")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_ACCESS)")
    public Page<ApplicationListRecordDTO> filterApplications(@RequestBody ApplicationListFilterDTO listFilter){
        BadRequestValidator.validateRequest(applicationListFilterValidator, listFilter);
        List<ApplicationListRecordDTO> resultList = commonApplicationService.getAllApplications(listFilter);
        Integer total = commonApplicationService.getTotalApplications(listFilter);
        return new Page<>(total, resultList, listFilter.getPageSize());
    }

    @GetMapping("/get-application-by-id")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_ACCESS)")
    public ApplicationListRecordDTO getApplicationById(@RequestParam Integer id){
        ApplicationListRecordDTO app = commonApplicationService.getApplicationById(id);
        return app;
    }

    @PostMapping("/accept-application")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_ACCEPT)")
    public ResponseEntity<byte[]> acceptApplication(@RequestBody AcceptApplicationRequestDTO acceptApplicationRequest){
        BadRequestValidator.validateRequest(acceptApplicationRequestValidator, acceptApplicationRequest);
        BaseApplicationService baseApplicationService = serviceHelper.getSpecificApplicationService(acceptApplicationRequest.getApplicationId());
        byte[] receiptBytes = baseApplicationService.changeApplicationToAccepted(acceptApplicationRequest);
        return ResponseEntity.ok(receiptBytes);
    }

    @PostMapping("/simply-change-application-status")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_ACCEPT)")
    public void simplyChangeApplicationStatus(@RequestBody ChangeFoApplicationStatusRequestDTO changeStatusRequest){
        BadRequestValidator.validateRequest(changeFoApplicationStatusRequestValidator, changeStatusRequest);
        BaseApplicationService baseApplicationService = serviceHelper.getSpecificApplicationService(changeStatusRequest.getApplicationId());
        baseApplicationService.changeFoApplicationStatus(changeStatusRequest);
    }

    @PostMapping("/accept-regprof-apostille-application")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_ACCEPT)")
    public void acceptRegprofApostilleApplication(@RequestBody AcceptApplicationRequestDTO acceptApplicationRequest){
        BadRequestValidator.validateRequest(acceptApplicationRequestValidator, acceptApplicationRequest);
        serviceHelper.getRegprofApostilleService().acceptRegprofApostilleApplication(acceptApplicationRequest);
    }

    @PostMapping("/revert-to-draft")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_STATUS_MODIFY)")
    public void revertApplicationToDraft(@RequestBody RevertApplicationStatusToDraftRequestDTO revertRequest){
        BadRequestValidator.validateRequest(revertApplicationStatusToDraftRequestValidator, revertRequest);
        BaseApplicationService baseApplicationService = serviceHelper.getSpecificApplicationService(revertRequest.getApplicationId());
        if(!baseApplicationService.applicationIsReversibleToDraft(revertRequest)){
            throw new BadRequestException("Not allowed to revert this app (may be it is apostille)");
        }
        baseApplicationService.revertApplicationToDraft(revertRequest);
    }

    @GetMapping("/get-temp-number")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_ACCESS)")
    public String getApplicationTempNumber(@RequestParam Integer id){
        return commonApplicationService.getApplicationTempNumber(id);
    }

    @GetMapping("/get-related-apps-from-multiple")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_ACCESS)")
    public List<ApplicationMultipleRecordDTO> getRelatedAppsFromMultiple(@RequestParam Integer singleApplicationId){
        return commonApplicationService.getRelatedAppsFromMultiple(singleApplicationId);
    }

    @GetMapping("/get-accepted-receipt")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_ACCESS)")
    public ResponseEntity<byte[]> getAcceptedReceiptContent(@RequestParam Integer id){
        byte[] acceptedReceiptBytes = commonApplicationService.getAcceptedReceipt(id);
        return ResponseEntity.ok(acceptedReceiptBytes);
    }
}
