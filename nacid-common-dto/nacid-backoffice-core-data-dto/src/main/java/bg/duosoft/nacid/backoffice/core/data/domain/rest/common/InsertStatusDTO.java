package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsertStatusDTO {
    private Integer applicationId;
    private String statusId;
    private Integer legalReasonId;
    private Integer calendarId;
    private String docflowStatusId;
}
