package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;


import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachedDocDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionApplicationDTO {
    private Integer id;
    private Integer applicationId;
    private Integer calendarId;
    private String motives;
    private String applicantInfo;
    private AttachedDocDTO attachedDoc;
}
