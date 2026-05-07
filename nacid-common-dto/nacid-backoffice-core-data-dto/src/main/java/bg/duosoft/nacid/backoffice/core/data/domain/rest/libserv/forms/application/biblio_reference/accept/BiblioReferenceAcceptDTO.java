package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.biblio_reference.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.accept.LibservAcceptBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BiblioReferenceAcceptDTO extends LibservAcceptBaseDTO {

    private BiblioReferenceAcceptViewDataDTO viewData;

}
