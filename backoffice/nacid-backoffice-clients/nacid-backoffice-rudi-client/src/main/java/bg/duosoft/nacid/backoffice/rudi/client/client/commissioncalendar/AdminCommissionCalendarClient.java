package bg.duosoft.nacid.backoffice.rudi.client.client.commissioncalendar;

import bg.duosoft.nacid.backoffice.rudi.client.config.ClientTokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AdminCommissionCalendarClient", url = "${feign.backoffice-rudi.base-url}/v1/commission-calendars", configuration = ClientTokenFeignConfig.class)
public interface AdminCommissionCalendarClient extends CommissionCalendarBaseClient {
}
