package bg.duosoft.nacidfrontofficedto.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.05.2022
 * Time: 15:01
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactAddressDTO extends Address {

    @Override
    public AddressType getAddressType() {
        return AddressType.CONTACT_ADDRESS;
    }
}
