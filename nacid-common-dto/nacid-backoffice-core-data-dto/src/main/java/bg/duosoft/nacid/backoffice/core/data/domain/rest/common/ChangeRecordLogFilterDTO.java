package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeRecordLogFilterDTO extends BaseFilterDTO {
    private String applicationName;
    private String id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String operation;
    private String responsibleUser;
    private String service;
    private String orderBy = "dateChanged";
}