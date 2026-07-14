package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
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
public class StatusDTO {
    private Boolean onlyActualStatus;
    private String status;
    private LocalDate statusDateFrom;
    private LocalDate statusDateTo;
    private String actualLegalStatus;
    private LocalDate actualLegalStatusDateFrom;
    private LocalDate actualLegalStatusDateTo;
    private List<IntegerIdDTO> actualLegalStatusLegalReasons;
    private String actualDocflowStatus;
    private LocalDate actualDocflowStatusDateFrom;
    private LocalDate actualDocflowStatusDateTo;
}
