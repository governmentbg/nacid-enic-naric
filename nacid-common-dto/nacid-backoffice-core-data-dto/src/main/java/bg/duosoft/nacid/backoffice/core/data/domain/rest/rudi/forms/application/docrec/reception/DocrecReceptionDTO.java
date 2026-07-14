package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.docrec.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.reception.RudiBaseReceptionDTO;
import lombok.Data;

@Data
public class DocrecReceptionDTO extends RudiBaseReceptionDTO {

    private String personalDocumentTypeId;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;

}
