package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.GraduationDocumentTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HigherTrainingCourseDTO {
    private Integer id;
    private ProfInstitutionDTO profInstitution;
    private ProfInstitutionFormerNameDTO profInstitutionFormerName;
    private GraduationDocumentTypeDTO graduationDocumentType;
    private String documentNumber;
    private String documentDate;
    private String documentSeries;
    private String documentRegNumber;
    private String professionalQualification;
    private String profQualificationModules;
    private ReferenceDataDTO educationLevel;
}
