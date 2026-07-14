package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.educationExperienceData.parts;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionalQualificationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.RPTrainingCourseSpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.educationExperienceData.util.SecondarySpecialityCutDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SecondaryEducationDataDTO {
    private Integer profInstitutionId;
    private Integer profInstitutionFormerNameId;
    private ReferenceDataDTO educationLevel;
    private Integer graduationDocumentTypeId;
    private String documentNumber;
    private String documentDate;
    private String documentSeries;
    private String documentRegNumber;
    private ReferenceDataDTO qualificationRank;
    private SecondaryProfessionalQualificationDTO secondaryProfessionalQualification;
    private String profQualificationModules;
    private List<SecondarySpecialityCutDTO> trainingCourseSpecialities;
}
