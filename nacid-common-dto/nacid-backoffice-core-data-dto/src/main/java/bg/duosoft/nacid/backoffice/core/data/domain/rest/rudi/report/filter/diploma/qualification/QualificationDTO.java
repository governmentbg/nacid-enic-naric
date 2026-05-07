package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.qualification;

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
public class QualificationDTO {
    private List<StringIdDTO> qualifications;
    private List<String> qualificationNames;
    private List<StringIdDTO> originalQualifications;
    private List<String> originalQualificationNames;
}
