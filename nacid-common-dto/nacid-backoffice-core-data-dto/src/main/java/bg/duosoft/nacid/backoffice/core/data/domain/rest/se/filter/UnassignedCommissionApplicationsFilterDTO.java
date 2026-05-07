package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.TextSearchType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.se.filter.sort.CommissionCalendarApplicationsSortUtils;
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
public class UnassignedCommissionApplicationsFilterDTO extends BaseFilterDTO {
    private Integer calendarId;
    private List<Integer> excludedApplicationIds;
    private String entryNum;
    private String responsibleUser;
    private String gradingSchoolCountryCode;
    private String applicantName;
    private TextSearchType applicantNameSearchType;
    private String subType;
    private String statusCode;
    private String orderBy = CommissionCalendarApplicationsSortUtils.ENTRY_DATE;
}
