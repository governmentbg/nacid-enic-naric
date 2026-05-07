package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.common.accept;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import lombok.Data;
import java.time.LocalDate;

@Data
public class RudiAcceptViewDataBaseDTO {

    private PersonDTO applicant;
    private PersonDTO representative;
    private PersonDTO representativeCompany;
    private AddressDTO contactAddress;
    private UniversityDTO baseUniversity;
    private String originalEduLevelTranslated;
    private String originalEduLevelName;
    private String diplomaOwnerEan;
    private LocalDate diplomaDate;
}
