package bg.duosoft.nacid.backoffice.core.data.domain.rest.regprof.forms.application.common.accept.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import lombok.Data;

@Data
public class RegprofAcceptViewDataDTO {

    private PersonDTO applicant;
    private PersonDTO representative;
    private PersonDTO representativeCompany;
    private AddressDTO contactAddress;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
}
