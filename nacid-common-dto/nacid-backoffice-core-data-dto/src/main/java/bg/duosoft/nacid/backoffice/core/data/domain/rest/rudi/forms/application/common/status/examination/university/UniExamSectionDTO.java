package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.examination.university;

import lombok.Data;

import java.util.List;

@Data
public class UniExamSectionDTO {
    private Integer applicationId;
    private List<UniExamSubsectionDTO> examinations;
}