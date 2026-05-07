package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.common;

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
public class NaturalPersonNamesReportDTO {
    private String firstName;
    private String middleName;
    private String lastName;
}
