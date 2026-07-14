package bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SqlExpressionFilterDTO {
    private String sql;
    private String[] requestParams;
}
