package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept.RudiAcceptBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UdirecAcceptDTO extends RudiAcceptBaseDTO {

    private UdirecAcceptViewDataDTO viewData;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;

}
