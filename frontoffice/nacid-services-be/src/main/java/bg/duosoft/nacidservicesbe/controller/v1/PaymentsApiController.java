package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.07.2023
 * Time: 13:32
 */
@Api(tags = Tags.PAYMENTS_API)
@RestController
@RequestMapping("/api/v1/payments-api")
@RequiredArgsConstructor
public class PaymentsApiController {

    private final CommonApplicationService commonApplicationService;

    @PostMapping("/notify-paid-change")
    @PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_PAID_MODIFY)")
    public void notifyPaidChange(@RequestParam String referenceNumber, @RequestParam String paymentStatusCode){
        commonApplicationService.changePaidFlag(referenceNumber, paymentStatusCode.equals("PAD"));
    }
}
