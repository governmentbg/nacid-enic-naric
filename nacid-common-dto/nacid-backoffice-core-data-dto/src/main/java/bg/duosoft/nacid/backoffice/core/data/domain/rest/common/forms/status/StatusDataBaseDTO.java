package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.status;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationNormalStatusHistoryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StatusDataBaseDTO {
    private Integer applicationId;
    private ReferenceDataDTO status;
    private List<ApplicationNormalStatusHistoryDTO> statusHistory;
}
