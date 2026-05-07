package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import lombok.Data;

@Data
public class LibservAcceptBaseDTO {

    private Integer applicantId;
    private Integer representativeId;
    private Integer representativeCompanyId;
    private Boolean representativeCompanyFlag;
    private Integer contactAddressId;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;

}
