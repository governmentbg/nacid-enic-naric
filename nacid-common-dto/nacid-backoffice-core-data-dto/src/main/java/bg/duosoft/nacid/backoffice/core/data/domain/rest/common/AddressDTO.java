package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.SettlementDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
    private Integer id;
    private String contactPerson;
    private String email;
    private String address;
    private String postCode;
    private String phone;
    private String fax;
    private CountryDTO country;
    private String city;
    private String postBox;
    private SettlementDTO settlement;
    private ReferenceDataDTO addressType;

}
