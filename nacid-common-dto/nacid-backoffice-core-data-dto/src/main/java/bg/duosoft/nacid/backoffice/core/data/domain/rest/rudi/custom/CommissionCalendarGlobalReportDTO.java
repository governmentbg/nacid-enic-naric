package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionCalendarGlobalReportDTO {
    private Integer documentType;
    private Boolean isDraft;
    private Integer calendarId;
    private List<Integer> applicationIds;
}
