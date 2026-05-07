package bg.duosoft.nacid.backoffice.core.data.domain.rest;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiApplicationsReportRequestDTO {
    private String template;
    private ReportType reportType;
    private List<Integer> applicationIds;
}
