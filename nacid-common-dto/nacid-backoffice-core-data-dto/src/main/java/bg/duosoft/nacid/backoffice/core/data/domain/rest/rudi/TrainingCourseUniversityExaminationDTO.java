package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainingCourseUniversityExaminationDTO {
    private Integer id;
    private UniversityDTO university;
    private String userCreated;
    private LocalDate examinationDate;
    private Boolean isCommunicated;
    private Boolean isRecognized;
    private String notes;
    private ReferenceDataDTO trainingLocation;
    private Boolean isJointDegree;
    private List<CompetentInstitutionDTO> competentInstitutions;
    private List<UniversityExaminationTrainingFormDTO> universityExaminationTrainingForms;
    private List<AttachedDocDTO> attachedDocs;
}
