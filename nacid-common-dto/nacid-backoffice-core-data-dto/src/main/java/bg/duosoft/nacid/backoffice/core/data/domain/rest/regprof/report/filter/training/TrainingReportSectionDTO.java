package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.training;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.report.filter.training.institution.ProfInstitutionReportDTO;
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
public class TrainingReportSectionDTO {
    private List<ProfInstitutionReportDTO> profInstitutions;
    private List<IntegerIdDTO> graduationDocTypes;
    private String educationTypeId;
    private List<IntegerIdDTO> secondaryQualificationIds;
    private List<StringIdDTO> qualifications;
    private List<String> qualificationNames;
    private List<IntegerIdDTO> secondarySpecialityIds;
    private List<StringIdDTO> specialities;
    private List<String> specialityNames;
}
