package bg.duosoft.nacidfrontofficedto.person;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 19.08.2022
 * Time: 14:06
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDTO {
    private String companyIdentifier;
    private CompanyIdentifierType companyIdentifierType = CompanyIdentifierType.BG_IDENTIFICATION_CODE;
    private String companyIdentifierTypeName;
    private CountryDTO companyCountry;
    private String companyName;
    private String companyCity;
    private SettlementDTO companySettlement;
}
