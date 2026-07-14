package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SEGradingScaleFormulaDTO implements Serializable {
    private String code;
    private String description;
    private Integer active;
}
