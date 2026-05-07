package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.teacher;

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
public class TeacherReportSectionDTO {
    private List<StringIdDTO> subjects;
    private List<String> subjectNames;
    private List<StringIdDTO> grades;
    private List<String> gradeNames;
    private List<StringIdDTO> types;
    private List<String> typeNames;
    private List<StringIdDTO> ageRanges;
    private List<String> ageRangeNames;
}
