package bg.duosoft.nacidservicesbe.mapper.common.address;

import bg.duosoft.nacidfrontofficedto.address.ContactAddressDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.AddressEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 24.10.2022
 * Time: 17:39
 */
@Mapper(componentModel = "spring", config = BaseAddressMapper.class)
public abstract class ContactAddressMapper extends BaseObjectMapper<AddressEntity, ContactAddressDTO> {

    @BeanMapping(ignoreByDefault = true)
    @InheritConfiguration(name = "baseAddressMapping")
    @Mapping(target = "fax", source = "fax")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "postBox", source = "postBox")
    @Mapping(target = "addressTypeCode", expression = "java(contactAddress.getAddressType().getCode())")
    public abstract AddressEntity toEntity(ContactAddressDTO contactAddress);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract ContactAddressDTO toDto(AddressEntity address);
}
