package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommissionDecisionsFilterDTO {
    private Integer calendarId;
    private String sortColumn;
    private Boolean ascOrder;
}
