package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveOptionFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.common.accept.accept.RegprofAcceptViewDataDTO;
import lombok.Data;

@Data
public class SEAcceptDTO {
    private SEAcceptViewDataDTO viewData;
    private Integer applicantId;
    private Integer representativeId;
    private Integer representativeCompanyId;
    private Boolean representativeCompanyFlag;
    private Integer contactAddressId;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
    private DocumentReceiveOptionFormDTO originalDocumentReceiveOption;
    private Boolean originalDocumentWaitingFlag;
}
