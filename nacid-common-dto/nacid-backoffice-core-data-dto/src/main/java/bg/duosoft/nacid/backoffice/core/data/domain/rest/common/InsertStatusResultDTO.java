package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertStatusResultDTO {
    private ApplicationStatusHistoryDTO status;
    private ApplicationDocflowStatusHistoryDTO docflowStatus;
}
