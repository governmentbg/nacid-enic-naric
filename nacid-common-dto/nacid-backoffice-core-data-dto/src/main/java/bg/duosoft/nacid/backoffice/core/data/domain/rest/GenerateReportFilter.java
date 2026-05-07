package bg.duosoft.nacid.backoffice.core.data.domain.rest;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateReportFilter {
    private Integer documentTypeId;
    private ReportType reportType;
    private List<Integer> applicationId;
    private Map<Integer, Map<String, Object>> customValues;
    private Map<Integer, Map<String, String>> metadata;
    private Boolean draft;
}
