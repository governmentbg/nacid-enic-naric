package bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StringIdNameDTO implements Serializable {
    private String id;
    private String name;
}
