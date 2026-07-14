package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationIdAndStatusDTO {
    private Integer applicationId;
    private String statusCode;
}
