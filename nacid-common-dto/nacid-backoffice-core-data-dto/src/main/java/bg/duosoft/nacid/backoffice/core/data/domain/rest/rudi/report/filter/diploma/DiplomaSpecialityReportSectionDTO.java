package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.edu_level.EduLevelDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.qualification.QualificationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.speciality.SpecialityDTO;
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
public class DiplomaSpecialityReportSectionDTO {
    private SpecialityDTO speciality;
    private QualificationDTO qualification;
    private EduLevelDTO eduLevel;
}
