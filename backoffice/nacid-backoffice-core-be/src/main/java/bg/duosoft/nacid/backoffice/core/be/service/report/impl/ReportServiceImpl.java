package bg.duosoft.nacid.backoffice.core.be.service.report.impl;

import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportFieldRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportSqlRepository;
import bg.duosoft.nacid.backoffice.core.be.service.report.ReportService;
import bg.duosoft.nacid.backoffice.core.be.service.report.impl.docx.DocxReportProcessorImpl;
import bg.duosoft.nacid.backoffice.core.be.service.report.impl.xlsx.XlsReportProcessorImpl;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportFieldEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportsResult;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * User: ggeorgiev
 * Date: 25.04.2023
 * Time: 16:17
 */
@Service("reportService")
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final XlsReportProcessorImpl xlsReportProcessor;
    private final DocxReportProcessorImpl docxReportProcessor;
    private final CfgReportFieldRepository cfgReportFieldRepository;
    private final CfgReportSqlRepository cfgReportSqlRepository;

    private ReportProcessorBaseImpl getReportService(ReportType rt) {
        return switch (rt) {
            case XLSX -> xlsReportProcessor;
            case DOCX, PDF -> docxReportProcessor;
        };
    }

    @Override
    public List<String> getDocumentGroupAndFieldNames(ReportType reportType, String templateName) {
        return getReportService(reportType).getDocumentGroupAndFieldNames(templateName);
    }

    @Override
    public byte[] generateFieldsConfigFile() {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Sheet1");
        List<CfgReportFieldEntity> mmfields = cfgReportFieldRepository.findAll();
        List<CfgReportSqlEntity> groups = cfgReportSqlRepository.findAllByGroupFlag(1);
        @AllArgsConstructor
        class ColumnCodeAndDescription {
            private String code;
            private String description;
        }

        List<ColumnCodeAndDescription> columnNames = new ArrayList<>();
        mmfields.stream().map(r -> new ColumnCodeAndDescription(r.getId(), r.getDescription())).forEach(columnNames::add);
        groups.stream().map(r -> new ColumnCodeAndDescription(r.getId(), r.getDescription())).forEach(columnNames::add);
        Collections.sort(columnNames, Comparator.comparing(r -> r.code));
        XSSFRow coderow = sheet.createRow(0);
        XSSFRow descriptionrow = sheet.createRow(1);
        for (int i = 0; i < columnNames.size(); i++) {
            XSSFCell cell = coderow.createCell(i );
            cell.setCellValue(columnNames.get(i).code);


            cell = descriptionrow.createCell(i );
            cell.setCellValue(columnNames.get(i).description);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bos.toByteArray();

    }

    @Override
    public byte[] generateReport(ReportType reportType, String templateName, Map<String, Object> sqlParams, Map<String, Object> customValues) {
        return getReportService(reportType).generateReport(reportType, templateName, sqlParams, customValues);
    }

    @Override
    public byte[] generateApplicationReport(ReportType reportType, String templateName, Integer applicationId, Integer commissionMemberId, Map<String, Object> customValues) {
        return getReportService(reportType).generateApplicationReport(reportType, templateName, applicationId, commissionMemberId, customValues);
    }

    @Override
    public byte[] generateCommissionReport(ReportType reportType, String templateName, Integer commissionCalendarId) {
        return getReportService(reportType).generateCommissionReport(reportType, templateName, commissionCalendarId);
    }

    @Override
    public byte[] generateApplicationReport(ReportType reportType, String templateName, Integer applicationId, Integer commissionMemberId) {
        return getReportService(reportType).generateApplicationReport(reportType, templateName, applicationId, commissionMemberId);
    }

    @Override
    public GenerateReportsResult generateApplicationReports(ReportType reportType, Integer documentTypeId, List<Integer> applicationIds, Map<Integer, Map<String, Object>> customValues,Map<Integer, Map<String, String>> metadata) {
        return getReportService(reportType).generateApplicationReports(reportType, documentTypeId, applicationIds, customValues,metadata);
    }
}
