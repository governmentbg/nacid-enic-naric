package bg.duosoft.nacid.backoffice.secondary.client.client.appreport;

import bg.duosoft.nacid.backoffice.secondary.client.config.SecContextFeignConfig;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "SEAppReportClient", url = "${feign.backoffice-secondary-education.base-url}/v1/applications/app-report", configuration = SecContextFeignConfig.class)
public interface SEAppReportClient {
    @GetMapping(value = "/generate/errors")
    List<ValidationError> getErrorsOnGenerateReport(@RequestParam("applicationId") Integer applicationId,
                                                    @RequestParam("documentTypeId") Integer documentTypeId,
                                                    @RequestParam(required = false,value = "attachmentId") Integer attachmentId);

    @GetMapping(value = "/last-commission-session-num")
    Integer getLastCommissionSessionNumReviewedTheApplication(@RequestParam("applicationId") Integer applicationId);
}
