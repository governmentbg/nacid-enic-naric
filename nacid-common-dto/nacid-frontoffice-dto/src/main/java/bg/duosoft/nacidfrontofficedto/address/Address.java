package bg.duosoft.nacidfrontofficedto.address;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.09.2022
 * Time: 15:08
 */
@Data
public abstract class Address extends BaseAddress {

    private String fax;
    private String email;
    private String postBox;
}
