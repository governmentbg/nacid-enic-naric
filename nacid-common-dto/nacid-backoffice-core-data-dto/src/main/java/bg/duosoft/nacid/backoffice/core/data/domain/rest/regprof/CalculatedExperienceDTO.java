package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculatedExperienceDTO {
    private Integer years;
    private Integer months;
    private Integer days;
}
