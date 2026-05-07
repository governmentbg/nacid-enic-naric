package bg.duosoft.nacid.backoffice.core.data.domain.rest.se.forms.application.common.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveOptionFormDTO;
import lombok.Data;

@Data
public class SEAcceptViewDataDTO {

    private PersonDTO applicant;
    private PersonDTO representative;
    private PersonDTO representativeCompany;
    private AddressDTO contactAddress;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
    private DocumentReceiveOptionFormDTO originalDocumentReceiveOption;
}
