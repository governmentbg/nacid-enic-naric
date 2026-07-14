package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.university;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.*;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom.UniversitySimpleDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UniExamSubsectionDTO {
    private Integer id;

    private UniversitySimpleDTO university;
    private LocalDate examinationDate;
    private Boolean isCommunicated;
    private Boolean isRecognized;
    private String notes;
    private String trainingLocationId;
    private Boolean isJointDegree;
    private List<CompetentInstitutionDTO> competentInstitutions;
    private List<String> trainingForms;
    private String otherTrainingFormNote;
    private Boolean isStatusUpdated;
    private List<AttachedDocDTO> attachedDocs;
}
