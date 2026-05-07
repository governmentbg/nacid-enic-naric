package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma;

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
public class RecognizedDiplomaSpecialityReportSectionDTO {
    private List<StringIdDTO> specialities;
    private List<String> specialityNames;
    private List<StringIdDTO> qualifications;
    private List<String> qualificationNames;
    private List<StringIdDTO> eduLevels;
}
