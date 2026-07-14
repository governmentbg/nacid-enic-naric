package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VCommissionCalendarDTO {
    private Integer id;
    private Integer sessionNum;
    private LocalDateTime sessionTime;
    private String statusName;
    private String statusCode;
}
