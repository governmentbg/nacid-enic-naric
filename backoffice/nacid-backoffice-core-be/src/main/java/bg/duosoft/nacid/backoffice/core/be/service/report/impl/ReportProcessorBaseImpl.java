package bg.duosoft.nacid.backoffice.core.be.service.report.impl;

import bg.duosoft.nacid.backoffice.core.be.service.common.ApplicationPropertiesService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.GenerateReportsResult;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationProperty;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * User: ggeorgiev
 * Date: 25.04.2023
 * Time: 16:19
 */
public abstract class ReportProcessorBaseImpl {
    @Autowired
    protected ApplicationPropertiesService applicationPropertiesService;
    protected Path getTemplatePath(String templateName) {
        String baseDir = Optional.ofNullable(applicationPropertiesService.selectById(ApplicationProperty.REPORTS_BASE_DIR.code())).orElseThrow(() -> new RuntimeException("Base dir not configured")).getValue();
        return Paths.get(baseDir + File.separator + templateName);
    }

    /**
     * @param templateName
     * @return list of all groups and fields in the template
     */
    protected abstract List<String> getDocumentGroupAndFieldNames(String templateName);


    protected abstract  byte[] generateReport(ReportType reportType, String templateName, Map<String, Object> sqlParams, Map<String, Object> customValues);

    protected byte[] generateApplicationReport(ReportType reportType, String templateName, Integer applicationId, Integer commissionMemberId, Map<String, Object> customValues) {
        Map<String, Object> sqlParams = new HashMap<>();
        sqlParams.put("applicationId", applicationId);
        if (commissionMemberId != null) {
            sqlParams.put("commissionMemberId", commissionMemberId);
        }
        return generateReport(reportType, templateName, sqlParams, customValues);
    }

    protected byte[] generateCommissionReport(ReportType reportType, String templateName, Integer commissionCalendarId) {
        Map<String, Object> sqlParams = Map.of("commissionCalendarId", commissionCalendarId);
        return generateReport(reportType, templateName, sqlParams, null);
    }

    protected byte[] generateApplicationReport(ReportType reportType, String templateName, Integer applicationId, Integer commissionMemberId) {
        return generateApplicationReport(reportType, templateName, applicationId, commissionMemberId,null);
    }


    protected abstract GenerateReportsResult generateApplicationReports(ReportType reportType, Integer documentTypeId, List<Integer> applicationIds, Map<Integer, Map<String, Object>> customValues,Map<Integer, Map<String, String>> metadata);
}
