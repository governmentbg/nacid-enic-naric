package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.udirec.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.RudiMainDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UdirecMainDataDTO extends RudiMainDataBaseDTO {

    private String personalDocumentTypeId;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;

}
