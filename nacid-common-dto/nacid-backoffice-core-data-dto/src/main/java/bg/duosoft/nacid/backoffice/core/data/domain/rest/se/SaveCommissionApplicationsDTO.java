package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveCommissionApplicationsDTO {
    private Integer calendarId;
    private List<Integer> applicationIds;
}
