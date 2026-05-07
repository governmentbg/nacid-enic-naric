package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.RudiMainDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocrecMainDataDTO extends RudiMainDataBaseDTO {

    private String personalDocumentTypeId;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;

}
