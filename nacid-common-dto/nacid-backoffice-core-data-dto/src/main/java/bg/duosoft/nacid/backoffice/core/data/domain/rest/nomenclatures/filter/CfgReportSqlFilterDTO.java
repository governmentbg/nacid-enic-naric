package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.BaseFilterDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CfgReportSqlFilterDTO extends BaseFilterDTO {
    private String id;
    private String description;
    private String sqlExpression;
    private Boolean manyRowsFlag;
    private Boolean groupFlag;
    private String startText;
    private String endText;
    private String separatorText;
    private String fieldId;
}
