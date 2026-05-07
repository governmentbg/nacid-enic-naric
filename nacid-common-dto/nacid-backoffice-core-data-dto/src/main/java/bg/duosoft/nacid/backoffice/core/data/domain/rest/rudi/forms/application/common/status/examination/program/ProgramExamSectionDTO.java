package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.program;

import lombok.Data;

@Data
public class ProgramExamSectionDTO {
    private Integer applicationId;
    private Boolean isLegitimate;
    private String programTypeId;
    private Boolean isStatusUpdated;
}
