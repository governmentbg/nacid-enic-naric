package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.summary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultipleAppDTO implements Serializable {

    private String multipleApplicationId;
    private Integer applicationId;
    private String refDataId;
    private String refDataName;
    private Boolean checked;
    private Boolean isHref;
    private Integer index;

}
