package bg.duosoft.nacidfrontofficedto.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.07.2022
 * Time: 16:48
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceiverAddressDTO extends BaseAddress {

    private String name;

    @Override
    public AddressType getAddressType() {
        return AddressType.RECEIVER_ADDRESS;
    }
}
