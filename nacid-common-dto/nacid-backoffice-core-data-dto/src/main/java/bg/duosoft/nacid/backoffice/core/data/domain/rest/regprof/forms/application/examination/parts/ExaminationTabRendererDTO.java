package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.examination.parts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExaminationTabRendererDTO {
    private boolean hasInstitutionLegitimacyExam;
    private boolean hasAuthDocExam;
    private boolean hasQualificationExam;
    private boolean hasExperienceExam;
}
