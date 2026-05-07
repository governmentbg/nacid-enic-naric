package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SECommissionMemberSaveDTO {
    private Integer calendarId;
    private List<SECommissionMemberRoleDTO> members;
}
