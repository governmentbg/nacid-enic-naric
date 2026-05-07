package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiplomaReportSectionDTO {
    private Boolean isStateApproved;
    private Integer diplomaYearFrom;
    private Integer diplomaYearTo;
}
