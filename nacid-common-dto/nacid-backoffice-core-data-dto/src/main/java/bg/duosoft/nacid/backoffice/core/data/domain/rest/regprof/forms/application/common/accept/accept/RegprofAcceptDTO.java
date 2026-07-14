package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.common.accept.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import lombok.Data;

@Data
public class RegprofAcceptDTO {

    private RegprofAcceptViewDataDTO viewData;
    private Integer applicantId;
    private Integer representativeId;
    private Integer representativeCompanyId;
    private Boolean representativeCompanyFlag;
    private Integer contactAddressId;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
}
