package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import lombok.Data;

import java.util.Map;

/**
 * User: ggeorgiev
 * Date: 10.10.2025
 * Time: 11:32
 */
@Data
public class OriginalProvidedReportRequestDTO {
    private ReportType reportType;
    private String templateName;
    private Integer applicationId;
    private Integer originalProvidedId;
    private Map<String, Object> customValues;
}
