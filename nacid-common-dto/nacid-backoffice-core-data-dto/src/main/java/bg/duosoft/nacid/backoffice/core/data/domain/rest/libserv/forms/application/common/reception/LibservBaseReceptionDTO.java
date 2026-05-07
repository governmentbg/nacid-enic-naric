package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.reception;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.main.mandatory.LibservMandatoryMainData;
import lombok.Data;

@Data
public class LibservBaseReceptionDTO implements LibservMandatoryMainData {
    private Integer applicantId;
    private Integer representativeId;
    private Integer representativeCompanyId;
    private Boolean representativeCompanyFlag;
    private String representativeCapacity;
    private Integer contactAddressId;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
}
