package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.Data;

/**
 * User: ggeorgiev
 * Date: 29.08.2022
 * Time: 14:54
 */
@Data
public class CfgAppStatusDTO {
    private Integer id;
    private ApplicationTypeDTO applicationType;
    private ApplicationSubtypeDTO applicationSubtype;
    private ReferenceDataDTO status;
    private Boolean isLegal;
    private Boolean isCommission;
    private Boolean isActive;
    private Boolean isInitialStatus;
    private Boolean isExecutionSuspendedStatus;
}
