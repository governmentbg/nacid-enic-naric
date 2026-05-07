package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 04.11.2022
 * Time: 14:23
 */
@Getter
@Setter
@NoArgsConstructor
public class CfgReportSqlDTO {
    private String id;
    private String description;
    private String sqlExpression;
    private Boolean manyRowsFlag;
    private Boolean groupFlag;
    private String startText;
    private String endText;
    private String separatorText;
    private List<CfgReportFieldDTO> fields;
    private LocalDateTime dateUpdated;
}
