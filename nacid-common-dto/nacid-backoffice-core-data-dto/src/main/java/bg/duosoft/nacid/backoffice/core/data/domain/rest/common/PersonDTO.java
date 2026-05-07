package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CivilIdTypeDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SettlementDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String otherName;
    private String latinFirstName;
    private String latinMiddleName;
    private String latinLastName;
    private String latinOtherName;
    private String legalName;
    private String civilId;
    private CivilIdTypeDTO civilIdType;
    private ReferenceDataDTO foreignIdentifierType;
    private CountryDTO foreignIdentifierCountry;
    private ReferenceDataDTO legalType;
    private ReferenceDataDTO legalNatureType;
    private ReferenceDataDTO humanitarianStatus;
    private CountryDTO originCountry;
    private String originCity;
    private SettlementDTO originSettlement;
    private LocalDate birthDate;
    private CountryDTO citizenship;
    private String email;
    private String phone;
    private String honorific;
    private Boolean isActive;

    private Integer connectedApplicationsCount;

    public PersonDTO(Integer id) {
        this.id = id;
    }
}
