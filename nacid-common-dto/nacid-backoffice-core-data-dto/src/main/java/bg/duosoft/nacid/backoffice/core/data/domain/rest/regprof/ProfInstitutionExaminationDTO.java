package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionalQualificationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfInstitutionExaminationDTO {
    private Integer id;
    private LocalDate examinationDate;
    private ProfInstitutionDTO profInstitution;
    private String higherProfQualification;
    private SecondaryProfessionalQualificationDTO secondaryProfQualification;
    private Boolean isLegitimate;
    private Boolean hasEducateRights;
    private Boolean isProgramLegitimate;
    private String userCreated;
    private LocalDateTime dateCreated;
    private String currentAccreditationDetails;
    private String archiveAccreditationDetails;
    private String notes;
}
