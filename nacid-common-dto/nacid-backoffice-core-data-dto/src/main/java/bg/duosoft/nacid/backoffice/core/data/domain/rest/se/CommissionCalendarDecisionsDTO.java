package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommissionCalendarDecisionsDTO extends CommissionApplicationsBaseDTO {
    private Integer id;
    private Integer calendarId;
    private Boolean generatedFinalDoc;
    private Boolean abdocsTransferred;
}
