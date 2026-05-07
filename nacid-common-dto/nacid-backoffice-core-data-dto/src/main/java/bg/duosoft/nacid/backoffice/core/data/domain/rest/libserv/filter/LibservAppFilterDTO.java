package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.TextSearchType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.filter.sort.LibservApplicationsSortUtils;
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
public class LibservAppFilterDTO extends BaseFilterDTO {
    private String entryNum;
    private Boolean entryNumExactMatch;
    private String additionalType;
    private String ateCode;
    private String aseCode;
    private String apnStatusCode;
    private String applicantName;
    private TextSearchType applicantNameSearchType;
    private String applicantCivilId;
    private String responsibleUser;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String filingType;

    private String orderBy = LibservApplicationsSortUtils.ID;
}
