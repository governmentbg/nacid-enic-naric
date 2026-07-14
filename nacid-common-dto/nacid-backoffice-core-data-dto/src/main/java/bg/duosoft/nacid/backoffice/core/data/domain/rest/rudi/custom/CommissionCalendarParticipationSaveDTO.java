package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionCalendarParticipationSaveDTO {
    private Integer calendarId;
    private List<CommissionCalendarParticipationCustomDTO> participations;
    private String secretary;
}
