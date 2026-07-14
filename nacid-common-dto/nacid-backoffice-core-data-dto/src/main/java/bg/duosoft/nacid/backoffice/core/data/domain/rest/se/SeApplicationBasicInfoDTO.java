package bg.duosoft.nacid.backoffice.core.data.domain.rest.se;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeApplicationBasicInfoDTO {
    private Integer id;
    private String applicationTypeCode;
    private String applicationSubtypeCode;
}
