package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.commission;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommissionReportSectionDTO {
    private Boolean isCommissionReviewed;
    private Boolean isNotCommissionReviewed;
    private LocalDate sessionDateFrom;
    private LocalDate sessionDateTo;
    private Integer sessionNumberFrom;
    private Integer sessionNumberTo;
    private List<CommissionStatusDTO> commissionStatuses;
}
