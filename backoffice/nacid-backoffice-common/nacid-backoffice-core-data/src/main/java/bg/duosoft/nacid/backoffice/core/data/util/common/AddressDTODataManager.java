package bg.duosoft.nacid.backoffice.core.data.util.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.CountryDTO;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class AddressDTODataManager {

    public static void setPredefinedData(AddressDTO address) {
        if (Objects.isNull(address)) {
            return;
        }

        ReferenceDataUtils.setDefaultDomain(address.getAddressType(), ReferenceDataDomain.ADDRESS_TYPE);

        setContactPersonData(address);
        setPostBoxData(address);
        setSettlementData(address);
    }

    private static void setContactPersonData(AddressDTO address) {
        if (!AddressUtils.isDocumentReceiveAddress(address)) {
            address.setContactPerson(null);
        }
    }

    private static void setPostBoxData(AddressDTO address) {
        if (!AddressUtils.isContactAddress(address) && !AddressUtils.isDocumentReceiveAddress(address)) {
            address.setPostBox(null);
        }
    }

    private static void setSettlementData(AddressDTO address) {
        CountryDTO country = address.getCountry();
        if (Objects.isNull(country)) {
            return;
        }

        String countryId = country.getId();
        if (!StringUtils.hasText(countryId)) {
            return;
        }

        if (DefaultValue.BG_COUNTRY_CODE.equals(countryId)) {
            address.setCity(null);
        } else {
            address.setSettlement(null);
        }
    }

}
