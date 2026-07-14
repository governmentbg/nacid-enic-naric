package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.sort.RudiApplicationsSortUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommissionCalendarFilterDTO  extends BaseFilterDTO {
    private String sessionStatusCode;
    private String sessionNum;
    private LocalDate sessionTimeFrom;
    private LocalDate sessionTimeTo;

    private String orderBy = RudiApplicationsSortUtils.SESSION_TIME;
}
