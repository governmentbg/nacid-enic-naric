package bg.duosoft.nacid.backoffice.core.be.service.report.impl.xlsx;

import org.apache.poi.ss.usermodel.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * User: ggeorgiev
 * Date: 25.04.2023
 * Time: 15:25
 */
public class POIUtils {
    private static Row createRowIfNotExists(Sheet sheet, int row) {
        if (sheet == null || row < sheet.getFirstRowNum() || row > sheet.getLastRowNum() ) {
            sheet.createRow(row);
        }
        Row r = sheet.getRow(row);
        if (r == null) {
            r = sheet.createRow(row);
        }
        return r;
    }
    private static Cell createCellIfNotExists(Sheet sheet, int row, int col) {
        Row r = createRowIfNotExists(sheet, row);
        if (col < r.getFirstCellNum() || col > r.getLastCellNum()) {
            r.createCell(col);
        }
        Cell cell = r.getCell(col);
        if (cell == null) {
            cell = r.createCell(col);
        }
        return cell;
    }
    public static Cell getCell(Sheet sheet, int row, int col) {
        if (sheet == null || row < sheet.getFirstRowNum() || row > sheet.getLastRowNum() ) {
            return null;
        }
        Row r = sheet.getRow(row);
        if (r == null) {
            return null;
        }
        if (col < r.getFirstCellNum() || col > r.getLastCellNum()) {
            return null;
        }

        return r.getCell(col) == null ? null : r.getCell(col);
    }
    private static CellValue getCellValue(Sheet sheet, int row, int col) {
        Cell cell = getCell(sheet, row, col);
        if (cell != null) {
            FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
            CellValue cellValue = evaluator.evaluate(cell);
            return cellValue;
        } else {
            return null;
        }

    }
    public static String getStringCellValue(Sheet sheet, int row, int col) {
        CellValue cellValue = getCellValue(sheet, row, col);
        if (cellValue != null) {
            if (cellValue.getCellType() == CellType.NUMERIC) {
                return String.valueOf(BigDecimal.valueOf(cellValue.getNumberValue()).setScale(0, RoundingMode.HALF_UP).toPlainString());
            } else if (cellValue.getCellType() == CellType.STRING) {
                return cellValue.getStringValue();
            }
        }
        return null;
    }

    public static Cell createCellIfNotExistsAndSetStringValue(Sheet sheet, int row, int col, String value) {
        Cell cell = createCellIfNotExists(sheet, row, col);
        setStringValue(sheet, row, col, value);
        return cell;
    }
    public static Cell createCellIfNotExistsAndSetBigDecimalValue(Sheet sheet, int row, int col, BigDecimal value) {
        Cell cell = createCellIfNotExists(sheet, row, col);
        if (value != null) {
            setBigDecimalValue(sheet, row, col, value);
        }
        return cell;

    }
    public static Cell createCellIfNotExistsAndSetNumberValue(Sheet sheet, int row, int col, Number value) {
        Cell cell = createCellIfNotExists(sheet, row, col);
        if (value != null) {
            setNumberValue(sheet, row, col, value);
        }
        return cell;
    }

    public static void setStringValue(Sheet sheet, int row, int col, String value) {
        Row r = sheet.getRow(row);
        if (r == null) {
            throw new RuntimeException("Unknown Cell...row=" + row + " col=" + col );
        }
        Cell cell = r.getCell(col);
        if (cell == null) {
            throw new RuntimeException("Unknown Cell...row=" + row + " col=" + col );
        }
        cell.setCellValue(value);
    }
    public static void setBigDecimalValue(Sheet sheet, int row, int col, BigDecimal value) {
        Cell cell = getCell(sheet, row, col);
        if (cell == null) {
            throw new RuntimeException("Unknown Cell...row=" + row + " col=" + col );
        }
        cell.setCellValue(value.doubleValue());
    }
    public static void setNumberValue(Sheet sheet, int row, int col, Number value) {
        Cell cell = getCell(sheet, row, col);
        if (cell == null) {
            throw new RuntimeException("Unknown Cell...row=" + row + " col=" + col );
        }
        cell.setCellValue(value.doubleValue());
    }
}
