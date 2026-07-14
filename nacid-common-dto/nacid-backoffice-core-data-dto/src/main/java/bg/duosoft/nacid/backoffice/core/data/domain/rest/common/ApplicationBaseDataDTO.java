package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplicationBaseDataDTO implements Serializable {
    private Integer id;
    private String applicationTypeCode;
    private String applicationSubTypeCode;
}
