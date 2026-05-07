package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionParticipationDTO {
    private Integer id;
    private CommissionMemberDTO commissionMember;
    private Boolean notified;
    private Boolean participated;
    private Boolean chairman;
}
