package bg.duosoft.nacidfrontofficedto.person;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import bg.duosoft.nacidfrontofficedto.utils.constants.DTOConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.05.2022
 * Time: 14:20
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaturalPersonDTO extends NaturalPersonNamesDTO implements WithPersonalIdentifier, WithEmail {

    private PersonalIdentifierType personalIdType;
    private String personalIdTypeName;
    private String personalId;
    private String personalNacidId;
    private ReferenceDataDTO foreignerIdentifierKind;
    private CountryDTO foreignerIdentifierCountry;
    private String email;
    private CountryDTO birthCountry;
    private String birthPlace;
    private SettlementDTO birthSettlement;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = DTOConstants.DATE_FORMAT)
    private LocalDate dateOfBirth;
    private CountryDTO citizenship;
    private String userName;
    private ReferenceDataDTO humanitarianStatus;
    private String title;

    public String getFullName(){
        return String.format("%s%s%s%s", getFirstName(),
                getMiddleName() != null && !getMiddleName().isBlank() ? " "+getMiddleName(): "",
                getLastName() != null && !getLastName().isBlank() ? " "+getLastName(): "",
                getOtherName() != null && !getOtherName().isBlank() ? " "+getOtherName(): "");
    }

    public String getLatinFullName(){
        return String.format("%s%s%s%s",
                getLatinFirstName() != null && !getLatinFirstName().isBlank() ? " "+getLatinFirstName(): "",
                getLatinMiddleName() != null && !getLatinMiddleName().isBlank() ? " "+getLatinMiddleName(): "",
                getLatinLastName() != null && !getLatinLastName().isBlank() ? " "+getLatinLastName(): "",
                getLatinOtherName() != null && !getLatinOtherName().isBlank() ? " "+getLatinOtherName(): "");
    }


}
