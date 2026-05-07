package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept.RudiAcceptBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocrecAcceptDTO extends RudiAcceptBaseDTO {

    private DocrecAcceptViewDataDTO viewData;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;

}
