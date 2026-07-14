package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.educationExperienceData.parts;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.RPTrainingCourseSpecialityDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PostgraduateEducationDataDTO {
    private Integer profInstitutionId;
    private Integer profInstitutionFormerNameId;
    private Integer graduationDocumentTypeId;
    private String documentNumber;
    private String documentDate;
    private String documentSeries;
    private String documentRegNumber;
    private String professionalQualification;
    private String profQualificationModules;
    private List<RPTrainingCourseSpecialityDTO> trainingCourseSpecialities;
}
