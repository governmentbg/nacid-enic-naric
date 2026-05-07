package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RudiCommissionApplicationsDTO extends RudiApplicationsBaseDTO {
    private Integer id;
    private Integer calendarId;
    private String motives;
    private String applicantInfo;
    private Boolean generatedFinalDoc;
    private Boolean abdocsTransferred;
}
