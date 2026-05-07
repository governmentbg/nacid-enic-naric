package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.forms.application.common.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.forms.DocumentReceiveMethodFormDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.DocumentReceiveMethodDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import lombok.Data;

@Data
public class LibservAcceptViewDataBaseDTO {

    private PersonDTO applicant;
    private PersonDTO representative;
    private PersonDTO representativeCompany;
    private AddressDTO contactAddress;
    private DocumentReceiveMethodFormDTO documentReceiveMethod;
}
