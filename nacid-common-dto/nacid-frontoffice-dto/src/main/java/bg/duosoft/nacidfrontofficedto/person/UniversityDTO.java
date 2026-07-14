package bg.duosoft.nacidfrontofficedto.person;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 18.01.2023
 * Time: 12:31
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UniversityDTO {

    private CompanyIdentifierType companyIdentifierType = CompanyIdentifierType.BG_IDENTIFICATION_CODE;
    private String universityIdentifier;
    private String universityIdentifierTypeName;
    private String universityName;
    private CountryDTO universityCountry;
    private SettlementDTO universitySettlement;
}
