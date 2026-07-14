package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationReportSectionDTO {
    private String entryNumber;
    private LocalDate applicationDateFrom;
    private LocalDate applicationDateTo;
    private LocalDate backofficeDateFrom;
    private LocalDate backofficeDateTo;
}
