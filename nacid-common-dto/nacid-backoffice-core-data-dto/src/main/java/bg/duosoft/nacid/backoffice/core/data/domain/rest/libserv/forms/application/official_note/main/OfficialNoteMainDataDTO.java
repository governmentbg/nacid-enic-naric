package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.official_note.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.main.LibservMainDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficialNoteMainDataDTO extends LibservMainDataBaseDTO {
    private String serviceTypeId;
}
