package bg.duosoft.nacidshareddata.service.report;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.01.2023
 * Time: 13:25
 */
public interface ReportService {

    byte[] generateReport(String template, String localeCode, Object... args);
}
