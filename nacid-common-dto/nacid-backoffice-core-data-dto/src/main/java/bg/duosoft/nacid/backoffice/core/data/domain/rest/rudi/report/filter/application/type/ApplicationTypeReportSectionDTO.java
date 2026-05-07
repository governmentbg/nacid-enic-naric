package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.type;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.JoinType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationTypeReportSectionDTO {
    private List<StringIdDTO> applicationTypes;
    private List<StringIdDTO> sarServices;
    private JoinType sarServicesJoin;
}
