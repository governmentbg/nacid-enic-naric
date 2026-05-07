package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationStatusHistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: ggeorgiev
 * Date: 25.01.2023
 * Time: 11:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SarApplicationDTO {
    private Boolean isStatute;
    private Boolean isAuthenticity;
    private Boolean isRecommendation;
    private String outgoingNumber;
    private String internalNumber;
    private ApplicationStatusHistoryDTO statuteFinalStatus;
    private ApplicationStatusHistoryDTO authenticityFinalStatus;
    private ApplicationStatusHistoryDTO recommendationFinalStatus;
}
