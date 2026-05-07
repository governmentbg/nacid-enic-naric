package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.university;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
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
public class UniversityDTO {
    private StringIdDTO country;
    private List<IntegerIdDTO> universities;
    private List<String> universityNames;
    private List<String> orgUniversityNames;
}
