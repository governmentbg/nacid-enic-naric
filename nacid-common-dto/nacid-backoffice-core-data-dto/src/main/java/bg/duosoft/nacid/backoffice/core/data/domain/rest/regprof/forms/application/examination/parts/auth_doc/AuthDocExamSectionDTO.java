package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.examination.parts.auth_doc;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdNameDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AuthDocExamSectionDTO {
    private Integer applicationId;
    private IntegerIdNameDTO profInstitution;
    private IntegerIdNameDTO graduationDocumentType;
    private String documentNumber;
    private String documentDate;
    private String documentSeries;
    private String documentRegNumber;
    private LocalDate examinationDate;
    private ReferenceDataDTO examinationSource;
    private List<AttachedDocDTO> attachedDocs;
}
