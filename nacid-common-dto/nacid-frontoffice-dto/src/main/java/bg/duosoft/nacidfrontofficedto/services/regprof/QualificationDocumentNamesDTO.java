package bg.duosoft.nacidfrontofficedto.services.regprof;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.person.NaturalPersonNamesDTO;
import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;
import bg.duosoft.nacidfrontofficedto.person.WithPersonalIdentifier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 11:49
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualificationDocumentNamesDTO extends NaturalPersonNamesDTO implements WithPersonalIdentifier {

    private PersonalIdentifierType personalIdType;
    private String personalId;
    private String personalNacidId;
    private ReferenceDataDTO foreignerIdentifierKind;
    private CountryDTO foreignerIdentifierCountry;
}
