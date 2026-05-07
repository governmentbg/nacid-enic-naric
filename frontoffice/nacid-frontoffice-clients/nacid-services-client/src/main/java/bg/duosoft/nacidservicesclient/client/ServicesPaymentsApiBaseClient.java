package bg.duosoft.nacidservicesclient.client;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.07.2023
 * Time: 10:50
 */
public interface ServicesPaymentsApiBaseClient {

    @PostMapping("/notify-paid-change")
    void notifyPaidChange(@RequestParam String referenceNumber, @RequestParam String paymentStatusCode);
}
