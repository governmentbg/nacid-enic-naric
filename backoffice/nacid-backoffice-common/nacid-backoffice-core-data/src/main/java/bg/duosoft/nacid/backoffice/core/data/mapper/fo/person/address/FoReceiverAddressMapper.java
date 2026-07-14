package bg.duosoft.nacid.backoffice.core.data.mapper.fo.person.address;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.AddressType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ReferenceDataDomain;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidfrontofficedto.address.ReceiverAddressDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", config = FoAddressMapperConfig.class)
public abstract class FoReceiverAddressMapper {

    @Mapping(target = "contactPerson", source = "name")
    @InheritConfiguration(name = "toBaseAddress")
    public abstract AddressDTO toReceiverAddress(ReceiverAddressDTO contactAddress);

    @AfterMapping
    protected void afterToReceiverAddress(ReceiverAddressDTO source, @MappingTarget AddressDTO target) {
        target.setAddressType(new ReferenceDataDTO(ReferenceDataDomain.ADDRESS_TYPE.domain(), AddressType.DOCUMENT.code()));
    }
}
