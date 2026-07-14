package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocflowStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RudiRegprofStatusDataBaseDTO extends StatusDataBaseDTO {
    private ReferenceDataDTO docflowStatus;
    private String archiveNumber;
    private List<ApplicationDocflowStatusHistoryDTO> docflowStatusHistory;
}
