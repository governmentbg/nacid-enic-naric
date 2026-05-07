package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept.RudiAcceptBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SarAcceptDTO extends RudiAcceptBaseDTO {
    private SarAcceptViewDataDTO viewData;
    private Integer diplomaOwnerId;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
}
