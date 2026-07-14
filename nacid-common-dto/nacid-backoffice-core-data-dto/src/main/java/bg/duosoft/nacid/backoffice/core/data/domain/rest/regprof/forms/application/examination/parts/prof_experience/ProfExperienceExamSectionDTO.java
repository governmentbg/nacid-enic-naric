package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.examination.parts.prof_experience;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfExperienceExamSectionDTO {
    private Integer applicationId;
    private String professionName;
    private Integer years;
    private Integer months;
    private Integer days;
    private Integer articleItemId;
    private Integer articleDirectiveId;
    private Boolean isExperienceDocumentRecognized;
    private Boolean hasArticleDirectiveWarning;
    private List<AttachedDocDTO> attachedDocs;
}
