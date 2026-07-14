package bg.duosoft.nacid.backoffice.core.data.util.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AddressType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.LegalType;

import java.util.Objects;

public class AddressUtils {

    public static boolean isContactAddress(AddressDTO address) {
        return doesMatchAddressType(address, AddressType.CONTACT);
    }

    public static boolean isDocumentReceiveAddress(AddressDTO address) {
        return doesMatchAddressType(address, AddressType.DOCUMENT);
    }

    private static boolean doesMatchAddressType(AddressDTO address, AddressType addressTypeEnum) {
        if (Objects.isNull(address) || ReferenceDataUtils.isEmptyRefDataId(address.getAddressType())) {
            return false;
        }

        String addressType = address.getAddressType().getId();
        return addressTypeEnum.code().equalsIgnoreCase(addressType);
    }


}
