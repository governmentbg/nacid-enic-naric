package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SEVCommissionCalendarDTO {
    private Integer id;
    private Integer sessionNum;
    private LocalDate sessionDate;
    private String statusName;
    private String statusCode;
    private String notes;
}
