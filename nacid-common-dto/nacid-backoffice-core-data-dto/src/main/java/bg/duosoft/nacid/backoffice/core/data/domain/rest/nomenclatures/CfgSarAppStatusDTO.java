package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:54
 */
@Data
public class CfgSarAppStatusDTO {
    private ReferenceDataDTO sarApplicationType;
    private ReferenceDataDTO status;
    private Boolean isPositive;

}
