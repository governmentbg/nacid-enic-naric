package bg.duosoft.nacid.backoffice.core.be.controller.v1.nomenclatures.report;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationPropertiesService;
import bg.duosoft.nacid.backoffice.core.be.service.report.ApplicationReportService;
import bg.duosoft.nacid.backoffice.core.be.service.report.ReportService;
import bg.duosoft.nacid.backoffice.core.be.service.report.impl.GlobalReportHelper;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportFilter;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportsResult;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.MultiApplicationsReportRequestDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.AppReportResultDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.file.AppReportTemplateDTO;
import bg.duosoft.nacidbackofficeshareddata.service.QrService;
import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidminioservices.service.FileStoreService;
import bg.duosoft.nacidshared.web.controller.BaseAccessController;
import bg.duosoft.nacidshareddata.exception.InternalServerErrorException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * User: ggeorgiev
 * Date: 02.11.2022
 * Time: 15:08
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.REPORTS)
@RequestMapping("/api/v1/report")
public class ReportController extends BaseAccessController {
    private final ReportService reportService;
    private final FileStoreService fileStoreService;
    private final ApplicationReportService applicationReportService;
    private final QrService qrService;
    private final ApplicationPropertiesService applicationPropertiesService;

    @GetMapping("/config-file")
    public void generateConfigFile(HttpServletResponse response) {
        byte[] bytes = reportService.generateFieldsConfigFile();
        writeData(response, bytes, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "fields.xlsx");
    }

    @GetMapping("/generate-application-report")
    public void generateReport(@RequestParam String template, @RequestParam ReportType reportType, @RequestParam Integer applicationId, @RequestParam(value = "commissionMemberId", required = false) Integer commissionMemberId, HttpServletResponse response) throws IOException {
        Map<String, Object> customValues = new HashMap<>();
        byte[] res = reportService.generateApplicationReport(reportType, template, applicationId, commissionMemberId, customValues);
        Path p = Paths.get(template);
        writeData(response, res, reportType.getMimeType(), FilenameUtils.removeExtension(p.getFileName().toString()) + "." + reportType.getExtension());
    }

    @PostMapping("/generate-multi-applications-report")
    public ResponseEntity<byte[]> generateMultiApplicationsReport(@RequestBody MultiApplicationsReportRequestDTO request) throws IOException {
        Map<String, Object> customValues = new HashMap<>();

        String template = request.getTemplate();
        ReportType reportType = request.getReportType();
        byte[] res = reportService.generateReport(reportType, template, Map.of("applicationIds", request.getApplicationIds()), customValues);
        Path p = Paths.get(template);

        String fileName = FilenameUtils.removeExtension(p.getFileName().toString()) + "." + reportType.getExtension();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-file-name", fileName);
        responseHeaders.set("Content-Disposition", String.format("%s;filename*=UTF-8''%s;filename=\"%s\"", "attachment", fileName, fileName));
        responseHeaders.set("Access-Control-Expose-Headers", "x-file-name");
        responseHeaders.set("Access-Control-Allow-Headers", "x-file-name");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .contentType(MediaType.valueOf(reportType.getMimeType()))
                .body(res);
    }

    @GetMapping("/generate-application-reports")
    public GenerateReportsResult generateReport(@RequestParam Integer documentTypeId, @RequestParam ReportType reportType, @RequestParam List<Integer> applicationId) {
        Map<Integer, Map<String, Object>> customValues = new HashMap<>();
        Map<Integer, Map<String, String>> metadata = new HashMap<>();
        return reportService.generateApplicationReports(reportType, documentTypeId, applicationId, customValues, metadata);
    }

    @GetMapping("/generate-commission-report")
    public void generateCommissionProtocol(@RequestParam String template, @RequestParam ReportType reportType, @RequestParam Integer commissionCalendarId, HttpServletResponse response) throws IOException {
        byte[] res = reportService.generateCommissionReport(reportType, template, commissionCalendarId);
        Path p = Paths.get(template);
        writeData(response, res, reportType.getMimeType(), FilenameUtils.removeExtension(p.getFileName().toString()) + "." + reportType.getExtension());
    }

    @PostMapping("/upload-application-reports")
    public List<AppReportResultDTO> uploadApplicationReports(@RequestBody AppReportTemplateDTO appReportTemplate) {
        List<AppReportResultDTO> appReportResultList = applicationReportService.generateApplicationReports(appReportTemplate);
        if (CollectionUtils.isEmpty(appReportResultList)) {
            throw new InternalServerErrorException("File store entry object is empty ! ApplicationId: " + appReportTemplate.getApplicationId());
        }

        for (AppReportResultDTO appReportResult : appReportResultList) {
            FileStoreEntryBaseDTO fileStoreEntryBaseDTO = fileStoreService.saveNewFile(appReportTemplate.getFileGroup(), appReportTemplate.getPointer(), appReportResult.getFile());
            appReportResult.setFile(fileStoreEntryBaseDTO);
        }
        return appReportResultList;
    }

    @PostMapping("/upload-global-report")
    public GenerateReportsResult uploadGlobalReport(@RequestBody GenerateReportFilter filter) {
        GlobalReportHelper.generateQRCodeImageByCertNumber(filter, qrService, applicationPropertiesService);
        return reportService.generateApplicationReports(filter.getReportType(), filter.getDocumentTypeId(), filter.getApplicationId(), filter.getCustomValues(), filter.getMetadata());
    }

    @Override
    public String getEditRole() {
        return null;
    }

    @Override
    public String getAccessRole() {
        return null;
    }

    public static void writeData(HttpServletResponse response, byte[] data, String contentType, String fileName) {
        try {
            if (Objects.nonNull(contentType))
                response.setContentType(contentType);
            if (Objects.nonNull(fileName))
                response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20"));

            OutputStream outputStream = response.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            response.flushBuffer();
            outputStream.close();
        } catch (IOException e) {
            //TODO
            log.error("Error writing image to response!", e);
            throw new RuntimeException(e);
        }
    }
}
