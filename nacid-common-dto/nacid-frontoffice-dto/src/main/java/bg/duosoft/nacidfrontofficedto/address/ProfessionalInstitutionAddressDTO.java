package bg.duosoft.nacidfrontofficedto.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.09.2022
 * Time: 15:14
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessionalInstitutionAddressDTO extends Address {

    @Override
    public AddressType getAddressType() {
        return AddressType.PROFESSIONAL_INSTITUTION_ADDRESS;
    }
}
