package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept.RudiAcceptViewDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocrecAcceptViewDataDTO extends RudiAcceptViewDataBaseDTO {
    private DocumentReceiveMethodFormDTO documentReceiveMethod;

}
