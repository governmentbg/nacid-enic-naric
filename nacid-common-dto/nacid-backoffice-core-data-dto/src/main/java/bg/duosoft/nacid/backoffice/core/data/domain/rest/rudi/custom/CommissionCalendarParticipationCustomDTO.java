package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionMemberDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionCalendarParticipationCustomDTO {
    private CommissionMemberDTO member;
    private Boolean notified;
    private Boolean participated;
    private Boolean chairman;
}
