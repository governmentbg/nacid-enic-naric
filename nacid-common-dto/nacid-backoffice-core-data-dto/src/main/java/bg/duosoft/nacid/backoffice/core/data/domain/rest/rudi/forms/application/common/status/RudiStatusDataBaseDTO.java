package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.status.RudiRegprofStatusDataBaseDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.LegalReasonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.status.mandatory.RudiMandatoryStatusData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RudiStatusDataBaseDTO extends RudiRegprofStatusDataBaseDTO implements RudiMandatoryStatusData {
    private LegalReasonDTO legalReason;
    private String submittedDocs;
}
