package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.official_note.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.accept.LibservAcceptBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficialNoteAcceptDTO extends LibservAcceptBaseDTO {

    private OfficialNoteAcceptViewDataDTO viewData;

}
