package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.TextSearchType;
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
public class SEApplicationsFilterDTO extends BaseFilterDTO {
    private String entryNum;
    private Boolean entryNumExactMatch;
    private String ateCode;
    private String aseCode;
    private String apnStatusCode;
    private String docflowStatusCode;
    private String applicantName;
    private TextSearchType applicantNameSearchType;
    private String representativeName;
    private TextSearchType representativeNameSearchType;
    private String schoolName;
    private String schoolGradingScaleCountry;
    private String applicantCivilId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate backofficeDateFrom;
    private LocalDate backofficeDateTo;
    private String responsibleUser;
    private String filingType;
    private String schoolCountry;
    private Integer suspendedFlag;
    private String certificateNumber;
    private LocalDate certificateDateFrom;
    private LocalDate certificateDateTo;
}
