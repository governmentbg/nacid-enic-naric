package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntegerIdDTO implements Serializable {
    private Integer id;
}
