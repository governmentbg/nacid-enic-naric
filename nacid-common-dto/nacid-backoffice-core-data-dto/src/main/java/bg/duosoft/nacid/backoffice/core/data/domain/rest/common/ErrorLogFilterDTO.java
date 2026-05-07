package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Pageable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
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
public class ErrorLogFilterDTO extends BaseFilterDTO {

    private String errorType;
    private String errorMessage;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
    private LocalDate resolvedDateFrom;
    private LocalDate resolvedDateTo;
    private String resolvedComment;
    private String resolvedUser;
    private String dataJson;
    private Boolean onlyUnresolved;

    private String orderBy = "createdDate";
    private String order = this.DESC_ORDER;
}
