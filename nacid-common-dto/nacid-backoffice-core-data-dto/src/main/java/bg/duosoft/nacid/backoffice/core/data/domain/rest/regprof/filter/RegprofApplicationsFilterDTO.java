package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.TextSearchType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegprofApplicationsFilterDTO extends BaseFilterDTO {
    private String entryNum;
    private Boolean entryNumExactMatch;
    private String ateCode;
    private String aseCode;
    private String apnStatusCode;
    private String docflowStatusCode;
    private String responsibleUser;
    private String applicantName;
    private TextSearchType applicantNameSearchType;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String filingType;
    private String applicantCivilId;
}
