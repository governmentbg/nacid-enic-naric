package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.submission_method;

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
public class SubmissionMethodReportSectionDTO {
    private Boolean onlyElectronic;
    private Boolean onlyDigitalSignature;
}
