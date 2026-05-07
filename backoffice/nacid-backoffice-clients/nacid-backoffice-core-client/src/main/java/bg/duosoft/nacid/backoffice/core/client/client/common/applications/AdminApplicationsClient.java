package bg.duosoft.nacid.backoffice.core.client.client.common.applications;

import bg.duosoft.nacid.backoffice.core.client.config.ClientTokenFeignConfig;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.05.2023
 * Time: 16:49
 */
@FeignClient(name = "AdminApplicationsClient", url = "${feign.backoffice-core.base-url}/v1/applications", configuration = ClientTokenFeignConfig.class)
public interface AdminApplicationsClient extends ApplicationsBaseClient {
    @PostMapping(value = "/update-application-paid-flag")
    public void updateApplicationPaidFlag(@RequestParam("entryNumber") String entryNumber,
                                         @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam("entryDate") LocalDate entryDate,
                                         @RequestParam("paymentFlag") Integer paymentFlag);

    @GetMapping(value = "/exists")
    public Boolean hasApplicationByEntryDetailsAndSubtype(@RequestParam("entryNumber") String entryNumber,
                                          @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam("entryDate") LocalDate entryDate,
                                          @RequestParam("subType") ApplicationSubType subType);

    @GetMapping(value = "/exists/by-app-type")
    Boolean hasApplicationByEntryDetailsAndAppType(@RequestParam("entryNumber") String entryNumber,
                                                   @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam("entryDate") LocalDate entryDate,
                                                   @RequestParam("appType") String appType);
}
