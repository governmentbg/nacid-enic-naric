package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.official_note.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.reception.LibservBaseReceptionDTO;
import lombok.Data;

import java.util.List;

@Data
public class OfficialNoteReceptionDTO extends LibservBaseReceptionDTO  {
    private List<String> kinds;
}
