package bg.duosoft.nacidfrontofficedto.address;

import bg.duosoft.nacidfrontofficedto.nomenclature.CountryDTO;
import bg.duosoft.nacidfrontofficedto.nomenclature.SettlementDTO;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.06.2022
 * Time: 12:06
 */
@Data
public abstract class BaseAddress {
    private CountryDTO country;
    private String city;
    private SettlementDTO settlement;
    private String postCode;
    private String address;
    private String phone;

    public abstract AddressType getAddressType();
}
