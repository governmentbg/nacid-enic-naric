package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCourseDiplomaExaminationDTO {
    private Integer id;
    private LocalDate examinationDate;
    private String notes;
    private Boolean isAuthentic;
    private Boolean isInstitutionCommunicated;
    private Boolean isUniversityCommunicated;
    private Boolean isFoundInRegister;
    private Boolean isStateApproved;
    private CompetentInstitutionDTO competentInstitution;
    private List<AttachedDocDTO> attachedDocs;
}
