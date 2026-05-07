package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.main;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.main.mandatory.LibservMandatoryMainData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibservMainDataBaseDTO implements LibservMandatoryMainData {
    private Integer applicationId;
    private Integer applicantId;
    private Integer representativeId;
    private Integer representativeCompanyId;
    private Boolean representativeCompanyFlag;
    private String representativeCapacity;
    private Integer contactAddressId;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
}
