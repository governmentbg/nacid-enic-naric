package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter;

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
public class RudiApplicationsFilterDTO extends BaseFilterDTO {
    private String entryNum;
    private Boolean entryNumExactMatch;
    private String ateCode;
    private String aseCode;
    private String apnStatusCode;
    private String docflowStatusCode;
    private String applicantName;
    private TextSearchType applicantNameSearchType;
    private String applicantCivilId;
    private String diplomaOwnerName;
    private TextSearchType diplomaOwnerNameSearchType;
    private String diplomaOwnerCivilId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate backofficeDateFrom;
    private LocalDate backofficeDateTo;
    private List<Integer> excludedApplications;
    private Integer universityId;
    private String universityName;
    private String universityCountryCode;
    private String responsibleUser;
    private String filingType;

}
