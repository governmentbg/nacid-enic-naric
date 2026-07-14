package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;

import lombok.Data;

@Data
public class ApplicationCommissionMemberStatementDTO {
    private Integer id;
    private CommissionMemberDTO commissionMember;
    private AttachedDocDTO attachedDoc;
}
