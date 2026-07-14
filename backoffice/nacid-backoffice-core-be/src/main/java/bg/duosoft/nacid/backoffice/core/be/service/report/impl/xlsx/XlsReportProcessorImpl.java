package bg.duosoft.nacid.backoffice.core.be.service.report.impl.xlsx;

import bg.duosoft.nacid.backoffice.core.be.repository.SqlRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportFieldRepository;
import bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.CfgReportSqlRepository;
import bg.duosoft.nacid.backoffice.core.be.service.report.impl.GroupSqlExecutor;
import bg.duosoft.nacid.backoffice.core.be.service.report.impl.ReportProcessorBaseImpl;
import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.CfgReportSqlEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportsResult;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 25.04.2023
 * Time: 14:29
 */
@Service
@RequiredArgsConstructor
public class XlsReportProcessorImpl extends ReportProcessorBaseImpl {
    protected final CfgReportFieldRepository cfgReportFieldRepository;
    protected final CfgReportSqlRepository cfgReportSqlRepository;
    protected final SqlRepository sqlRepository;
    private final GroupSqlExecutor groupSqlExecutor;

    @Override
    protected List<String> getDocumentGroupAndFieldNames(String templateName) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook(getTemplatePath(templateName).toString());
            XSSFSheet sheet = wb.getSheetAt(0);
            return getFieldNames(sheet).stream().map(r -> r.fieldName).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] generateReport(ReportType reportType, String templateName, Map<String, Object> sqlParams, Map<String, Object> customValues) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook(getTemplatePath(templateName).toString());
            XSSFSheet sheet = wb.getSheetAt(0);
            List<FieldNameAndIndex> fieldNames = getFieldNames(sheet);
            CfgReportSqlEntity config = getSqlConfig(fieldNames);

            Map<String, List<Map<String, Object>>> groupValues = groupSqlExecutor.getGroupValues(new String[0], new String[]{config.getId()}, customValues, sqlParams);


            processResult(fieldNames, groupValues.get(config.getId()), sheet);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            wb.write(bos);
            return bos.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected GenerateReportsResult generateApplicationReports(ReportType reportType, Integer documentTypeId, List<Integer> applicationIds, Map<Integer, Map<String, Object>> customValues,Map<Integer, Map<String, String>> metadata) {
        throw new RuntimeException("Not Supported");
    }

    private void processResult(List<FieldNameAndIndex> fieldNames, List<Map<String, Object>> groupExecutionResult, Sheet sheet) {
        int rowNum = fieldNames.get(0).row;
        if (groupExecutionResult.size() == 0) {
            initEmptyGroupExecutionResult(fieldNames, groupExecutionResult);
        }
        for (Map<String, Object> res : groupExecutionResult) {
            for (FieldNameAndIndex fn : fieldNames) {
                Object fv = res.get(fn.fieldName);
                Cell oldCell = POIUtils.getCell(sheet, fn.row, fn.col);
                Cell newCell;
                if (fv instanceof BigDecimal d) {
                    newCell = POIUtils.createCellIfNotExistsAndSetBigDecimalValue(sheet, rowNum, fn.col, d);
                } else if (fv instanceof Number n) {
                    newCell = POIUtils.createCellIfNotExistsAndSetNumberValue(sheet, rowNum, fn.col, n);
                } else {
                    newCell = POIUtils.createCellIfNotExistsAndSetStringValue(sheet, rowNum, fn.col, fv == null ? "" : fv.toString());
                }
                newCell.setCellStyle(oldCell.getCellStyle());

            }
            rowNum++;
        }
    }
    private void initEmptyGroupExecutionResult(List<FieldNameAndIndex> fieldNames, List<Map<String, Object>> groupExecutionResult) {
        Map<String, Object> row = new HashMap<>();
        for (FieldNameAndIndex fn : fieldNames) {
            row.put(fn.fieldName, "");
        }
        groupExecutionResult.add(row);
    }

    private CfgReportSqlEntity getSqlConfig(List<FieldNameAndIndex> fieldNames) {
        List<CfgReportSqlEntity> sqlConfigs = cfgReportSqlRepository.findAllByFieldNames(fieldNames.stream().map(r -> r.fieldName).collect(Collectors.toSet()));
        if (sqlConfigs.size() != 1 || sqlConfigs.get(0).getGroupFlag() != 1) {
            throw new RuntimeException("Cannot generate xlsx file. All the fields in the file should be from a single sql and the sql's group flag should be true! " + sqlConfigs);
        }
        return sqlConfigs.get(0);
    }


    @AllArgsConstructor
    private class FieldNameAndIndex {
        private String fieldName;
        private int row;
        private int col;
    }

    private List<FieldNameAndIndex> getFieldNames(Sheet sheet) {
        List<FieldNameAndIndex> fieldNames = new ArrayList<>();
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            Row r = sheet.getRow(i);
            for (int j = r.getFirstCellNum(); j <= r.getLastCellNum(); j++) {
                String cellValue = POIUtils.getStringCellValue(sheet, i, j);
                if (cellValue != null && cellValue.matches("<<.*?>>")) {
                    fieldNames.add(new FieldNameAndIndex(cellValue.replace("<<", "").replace(">>",""), i, j));
                }
            }
        }
        return fieldNames;
    }

}
