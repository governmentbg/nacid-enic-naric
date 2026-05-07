package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SECommissionMemberDTO {
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
}
