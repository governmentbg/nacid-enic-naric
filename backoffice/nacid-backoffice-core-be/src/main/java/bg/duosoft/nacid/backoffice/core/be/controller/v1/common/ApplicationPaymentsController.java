package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationPaymentsService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import bg.duosoft.nacid.payments.dto.payments.LiabilityDetailDTO;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.APPLICATION_PAYMENTS)
@RequestMapping("/api/v1/application-payments")
public class ApplicationPaymentsController extends BaseAccessController {
    private final ApplicationPaymentsService applicationPaymentsService;


    @Override
    public String getEditRole() {
        return SecurityRole.CORE_APPLICATION_EDIT;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @PostMapping
    @ApiOperation(value = "Save payments details")
    public void save(@RequestParam("applicationId") Integer applicationId, @RequestBody LiabilityDetailDTO liabilityDetail) {
        applicationPaymentsService.save(applicationId, liabilityDetail);
    }

    @DeleteMapping()
    @ApiOperation("Delete liability detail")
    public void delete(@RequestParam("liabilityId") Integer liabilityId, @RequestParam("liabilityDetailId") Integer liabilityDetailId) {
        applicationPaymentsService.delete(liabilityId, liabilityDetailId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/insert-fees")
    @ApiOperation(value = "Save payments details")
    public void insertFees(@RequestParam("applicationId") Integer applicationId) {
        applicationPaymentsService.insertFees(applicationId);
    }


}
