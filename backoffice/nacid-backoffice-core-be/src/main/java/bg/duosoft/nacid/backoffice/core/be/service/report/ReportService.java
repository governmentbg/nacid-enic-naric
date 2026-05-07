package bg.duosoft.nacid.backoffice.core.be.service.report;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportsResult;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;

import java.util.List;
import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 04.11.2022
 * Time: 14:13
 */
public interface ReportService {

    /**
     * @param templateName
     * @return list of all groups and fields in the template
     */
    public List<String> getDocumentGroupAndFieldNames(ReportType reportType, String templateName);

    /**
     * @return xlsx file with all the mail merge fields
     */
    byte[] generateFieldsConfigFile();

    /**
     * @param templateName - template name
     * @param sqlParams - params, sent to the mail merge's sqls (for an example - "applicationId" -> 1)
     * @param customValues - custom mail merge parameter + values, that are not getting executed from the mail merge sqls, but are sent directly to the doc file (if any) - for an example the barcode image might not be read from the database, but generated on the fly!
     * @return
     */
    byte[] generateReport(ReportType reportType, String templateName, Map<String, Object> sqlParams, Map<String, Object> customValues);

    byte[] generateApplicationReport(ReportType reportType, String templateName, Integer applicationId, Integer commissionMemberId, Map<String, Object> customValues);

    public byte[] generateCommissionReport(ReportType reportType, String templateName, Integer commissionCalendarId);

    byte[] generateApplicationReport(ReportType reportType, String templateName, Integer applicationId, Integer commissionMemberId);


    GenerateReportsResult generateApplicationReports(ReportType reportType, Integer documentTypeId, List<Integer> applicationIds, Map<Integer, Map<String, Object>> customValues,Map<Integer, Map<String, String>> metadata);
}
