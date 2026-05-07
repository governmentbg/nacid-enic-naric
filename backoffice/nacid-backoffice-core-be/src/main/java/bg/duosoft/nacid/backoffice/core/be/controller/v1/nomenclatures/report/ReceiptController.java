package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures.report;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationsService;
import bg.duosoft.nacid.backoffice.core.be.service.report.ReportService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import bg.duosoft.nacidshareddata.exception.InternalServerErrorException;
import bg.duosoft.nacidshareddata.util.ResponseUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.REPORTS)
@RequestMapping("/api/v1/report/receipt")
public class ReceiptController {

    private final ApplicationsService applicationsService;
    private final ReportService reportService;

    @GetMapping("/generate/{id}")
    public ResponseEntity<byte[]> generateReceipt(@PathVariable("id") Integer id) {
        ApplicationDTO application = ResponseUtils.notFoundCheck(applicationsService.getApplicationById(id));
        String templateName = getTemplateName(application);
        byte[] report = reportService.generateApplicationReport(ReportType.PDF, templateName, id, null, null);

        return ResponseEntity.ok()
                .header("Content-Disposition", String.format("%s;filename*=UTF-8''%s;filename=\"%s\"", "attachment", "receipt-" + id + ".pdf", "receipt-" + id + ".pdf"))
                .contentType(MediaType.APPLICATION_PDF)
                .body(report);
    }

    private String getTemplateName(ApplicationDTO application) {
        ApplicationType type = ApplicationType.selectByCode(application.getApplicationType().getId());
        return switch (type) {
            case RUDI -> "rudi/rudi_receipt.docx";
            case REGPROF -> "regprof/regprof_receipt.docx";
            case LIBSERV -> "libserv/libserv_receipt.docx";
        };
    }

}
