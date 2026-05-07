package bg.duosoft.nacid.backoffice.rudi.be.controller.v1.report;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.Page;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.RudiApplicationsDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.RudiCommonReportFilterDTO;
import bg.duosoft.nacid.backoffice.rudi.be.service.CommonReportService;
import bg.duosoft.nacid.backoffice.rudi.be.util.swagger.Tags;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.REPORT)
@RequestMapping("/api/v1/report")
public class ReportController extends BaseAccessController {
    private final CommonReportService commonReportService;

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    @PostMapping("/common-report")
    @ApiOperation(value = "Filter common report records")
    public Page<RudiApplicationsDTO> filterCommonData(@RequestBody RudiCommonReportFilterDTO filter) {
       return new Page<>(commonReportService.getReportApplicationsCount(filter), commonReportService.getReportApplications(filter), filter.getPageSize());
    }

    @PostMapping(value = "/common-report-generation")
    @ApiOperation(value = "Generate common report excel")
    public ResponseEntity<byte[]> generateReport(@RequestBody RudiCommonReportFilterDTO filter) {
        return commonReportService.generateReport(filter);
    }

}
