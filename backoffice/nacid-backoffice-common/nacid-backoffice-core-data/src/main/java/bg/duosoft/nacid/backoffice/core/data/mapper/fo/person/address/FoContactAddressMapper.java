package bg.duosoft.nacid.backoffice.core.data.mapper.fo.person.address;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AddressType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.address.ContactAddressDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", config = FoAddressMapperConfig.class)
public abstract class FoContactAddressMapper {

    @Mapping(target = "fax", source = "fax")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "postCode", source = "postCode")
    @InheritConfiguration(name = "toBaseAddress")
    public abstract AddressDTO toContactAddress(ContactAddressDTO contactAddress);

    @AfterMapping
    protected void afterToContactAddress(ContactAddressDTO source, @MappingTarget AddressDTO target) {
      target.setAddressType(new ReferenceDataDTO(ReferenceDataDomain.ADDRESS_TYPE.domain(), AddressType.CONTACT.code()));
    }
}
