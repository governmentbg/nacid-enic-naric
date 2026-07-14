package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.main.RudiMainDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SarMainDataDTO extends RudiMainDataBaseDTO {

    private Integer diplomaOwnerId;
    private String diplomaOwnerEan;
    private String outgoingNumber;
    private String internalNumber;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
}
