package bg.duosoft.nacid.backoffice.core.client.client.common.report;

import bg.duosoft.nacid.backoffice.core.client.config.SecContextFeignConfig;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportFilter;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportsResult;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.MultiApplicationsReportRequestDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.AppReportTemplateDTO;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.IOException;


@FeignClient(name = "ReportClient", url = "${feign.backoffice-core.base-url}/v1/report", configuration = SecContextFeignConfig.class)
public interface ReportClient {
    @PostMapping("/upload-application-reports")
    FileStoreEntryBaseDTO uploadApplicationReport(@RequestBody AppReportTemplateDTO appReportTemplate);

    @PostMapping("/upload-global-report")
    GenerateReportsResult uploadGlobalReport(@RequestBody GenerateReportFilter filter);

    @PostMapping("/generate-multi-applications-report")
    ResponseEntity<byte[]> generateMultiApplicationsReport(@RequestBody MultiApplicationsReportRequestDTO request) throws IOException;
}
