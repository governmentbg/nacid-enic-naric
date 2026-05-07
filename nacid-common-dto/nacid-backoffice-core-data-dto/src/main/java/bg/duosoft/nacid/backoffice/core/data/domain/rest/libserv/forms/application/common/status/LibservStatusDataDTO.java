package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNotesDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.status.StatusDataBaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class LibservStatusDataDTO extends StatusDataBaseDTO {
    private String responsibleUser;
    private String userCreatedFullName;
    private List<String> responsibleUsersHistory;
    private List<ApplicationNotesDTO> applicationNotes;
}
