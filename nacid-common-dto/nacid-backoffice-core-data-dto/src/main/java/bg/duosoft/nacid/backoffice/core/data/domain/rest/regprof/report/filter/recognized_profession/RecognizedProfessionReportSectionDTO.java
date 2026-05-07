package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.recognized_profession;

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
public class RecognizedProfessionReportSectionDTO {
    private List<StringIdDTO> recognizedProfessions;
    private List<String> recognizedProfessionNames;
}
