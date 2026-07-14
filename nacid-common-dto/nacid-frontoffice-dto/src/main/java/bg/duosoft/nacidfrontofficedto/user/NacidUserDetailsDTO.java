package bg.duosoft.nacidfrontofficedto.user;

import bg.duosoft.nacidfrontofficedto.address.ContactAddressDTO;
import bg.duosoft.nacidfrontofficedto.address.ReceiverAddressDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveMethodDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import bg.duosoft.nacidfrontofficedto.person.PersonalIdentifierType;
import bg.duosoft.nacidfrontofficedto.utils.constants.DTOConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 22.06.2022
 * Time: 15:08
 */
@Data
public class NacidUserDetailsDTO extends BaseUserDetailsDTO {

    private String password;
    private String middleName;
    private PersonalIdentifierType personalIdType;
    private String personalId;
    private String personalNacidId;
    private CountryDTO citizenship;
    private CountryDTO birthCountry;
    private String birthPlace;
    private SettlementDTO birthSettlement;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = DTOConstants.DATE_FORMAT)
    private LocalDate dateOfBirth;
    private ReferenceDataDTO foreignerIdentifierKind;
    private CountryDTO foreignerIdentifierCountry;
    private Boolean isRepresentative;
    private UserRepresentativeType representativeType;
    private String representedUniversity;
    private String representedCompany;
    private String representativeCapacity;
    private ContactAddressDTO contactAddress;
    private DocumentReceiveMethodDTO resultReceive;
    private ReceiverAddressDTO receiverAddress;
    private String nacidEmployeePosition;
    private Boolean emailVerified;
    private Boolean enabled;
    private Long createdTimestamp;
    private ReferenceDataDTO humanitarianStatus;
    private String title;

    @Override
    public String getFullName(){
        return String.format("%s %s%s", getFirstName(), middleName != null ? middleName+" ": "", getLastName());
    }

    public String getFirstAndLastName(){
        return String.format("%s %s", getFirstName(), getLastName());
    }

    public String getNacidEmployeeNameAndPosition() {
        return getFullName() + ((getNacidEmployeePosition() == null || getNacidEmployeePosition().isEmpty())? "" : " - " + getNacidEmployeePosition());
    }
}
