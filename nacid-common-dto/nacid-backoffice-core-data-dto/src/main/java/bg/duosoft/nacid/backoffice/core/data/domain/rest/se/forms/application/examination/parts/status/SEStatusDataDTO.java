package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.examination.parts.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.status.RudiRegprofStatusDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SEStatusDataDTO extends RudiRegprofStatusDataBaseDTO {
    private LegalReasonDTO legalReason;
    private String motives;
}
