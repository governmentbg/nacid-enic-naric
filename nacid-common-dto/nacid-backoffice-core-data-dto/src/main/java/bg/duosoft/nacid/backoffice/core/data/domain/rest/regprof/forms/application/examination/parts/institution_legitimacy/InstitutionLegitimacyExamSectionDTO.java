package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.examination.parts.institution_legitimacy;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdNameDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SecondaryProfessionalQualificationDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InstitutionLegitimacyExamSectionDTO {
    private Integer applicationId;
    private Integer examinationId;
    private ReferenceDataDTO educationType;
    private IntegerIdNameDTO profInstitution;
    private String higherProfQualification;
    private SecondaryProfessionalQualificationDTO secondaryProfQualification;
    private LocalDate examinationDate;
    private Boolean isLegitimate;
    private Boolean isProgramLegitimate;
    private Boolean hasEducateRights;
    private String currentAccreditationDetails;
    private String archiveAccreditationDetails;
    private String notes;
}
