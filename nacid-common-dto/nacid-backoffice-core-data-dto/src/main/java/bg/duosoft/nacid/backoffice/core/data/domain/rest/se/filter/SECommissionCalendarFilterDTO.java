package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.se.filter.sort.SEApplicationsSortUtils;
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
public class SECommissionCalendarFilterDTO extends BaseFilterDTO {
    private String sessionStatusCode;
    private LocalDate sessionDateFrom;
    private LocalDate sessionDateTo;

    private String orderBy = SEApplicationsSortUtils.SESSION_DATE;
}
