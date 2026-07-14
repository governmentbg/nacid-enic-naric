package bg.duosoft.nacidfrontofficedto.person;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 30.08.2022
 * Time: 14:08
 */
public interface WithPersonalIdentifier {

    PersonalIdentifierType getPersonalIdType();
    void setPersonalIdType(PersonalIdentifierType identifierType);
    String getPersonalId();
    void setPersonalId(String identifier);
    String getPersonalNacidId();
    void setPersonalNacidId(String identifier);
    ReferenceDataDTO getForeignerIdentifierKind();
    void setForeignerIdentifierKind(ReferenceDataDTO foreignerIdentifierKind);
    CountryDTO getForeignerIdentifierCountry();
    void setForeignerIdentifierCountry(CountryDTO foreignerIdentifierCountry);
}
