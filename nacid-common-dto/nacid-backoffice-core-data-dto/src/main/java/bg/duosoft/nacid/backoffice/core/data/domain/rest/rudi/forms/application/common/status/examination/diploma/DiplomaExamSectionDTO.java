package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.diploma;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiplomaExamSectionDTO {
    private Integer applicationId;
    private LocalDate examinationDate;
    private String notes;
    private Boolean isAuthentic;
    private Boolean isInstitutionCommunicated;
    private Boolean isUniversityCommunicated;
    private Boolean isFoundInRegister;
    private Boolean isStateApproved;
    private Integer competentInstitutionId;
    private List<String> universityCountryIds;
    private List<String> universityNames;
    private Boolean isStatusUpdated;
    private List<AttachedDocDTO> attachedDocs;
}
