package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidservicesbe.service.BaseApplicationService;
import bg.duosoft.nacidservicesbe.service.ServiceHelper;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 23.08.2023
 * Time: 11:13
 */
@RestController
@Api(tags = Tags.APPLICATION_ADMINISTRATION)
@RequestMapping("/admin/application")
@RequiredArgsConstructor
@PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_ACCEPT)")
public class AdminApplicationController {

    private final ServiceHelper serviceHelper;

    @PostMapping("/regenerate-receipt")
    public ResponseEntity<byte[]> regenerateReceipt(@RequestParam Integer applicationId,
                                                    @RequestParam boolean addToApplication,
                                                    @RequestParam(required = false, defaultValue = "true") boolean keepOld,
                                                    @RequestParam FoApplicationStatus status){
        if(status.equals(FoApplicationStatus.DRAFT) || status.equals(FoApplicationStatus.SUBMITTED_WITH_SIGNATURE)){
            throw new BadRequestException("Can not regenerate receipt with status "+status);
        }

        BaseApplicationService baseApplicationService = serviceHelper.getSpecificApplicationService(applicationId);
        byte[] receiptBytes = baseApplicationService.regenerateReceipt(applicationId, addToApplication, keepOld, status);
        return ResponseEntity.ok(receiptBytes);
    }

    @PostMapping("/regenerate-regprof-apostille-receipt")
    public ResponseEntity<byte[]> regenerateReceipt(@RequestParam Integer applicationId,
                                                    @RequestParam boolean addToApplication,
                                                    @RequestParam(required = false, defaultValue = "true") boolean keepOld){

        byte[] receiptBytes = serviceHelper.getRegprofApostilleService().regenerateReceipt(applicationId, addToApplication, keepOld);
        return ResponseEntity.ok(receiptBytes);
    }
}
