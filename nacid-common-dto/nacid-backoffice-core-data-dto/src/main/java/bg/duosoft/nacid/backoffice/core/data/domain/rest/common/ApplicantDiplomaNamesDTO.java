package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicantDiplomaNamesDTO implements Serializable {

    private String firstName;
    private String middleName;
    private String lastName;
    private String otherName;
    private String latinFirstName;
    private String latinMiddleName;
    private String latinLastName;
    private String latinOtherName;
    private String civilId;
    private CivilIdTypeDTO civilIdType;
    private ReferenceDataDTO foreignIdentifierType;
    private CountryDTO foreignIdentifierCountry;

}
