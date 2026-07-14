package bg.duosoft.nacidservicesbe.mapper.common.address;

import bg.duosoft.nacidfrontofficedto.address.ReceiverAddressDTO;
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
public abstract class ReceiverAddressMapper extends BaseObjectMapper<AddressEntity, ReceiverAddressDTO> {

    @BeanMapping(ignoreByDefault = true)
    @InheritConfiguration(name = "baseAddressMapping")
    @Mapping(target = "contactPerson", source = "name")
    @Mapping(target = "addressTypeCode", expression = "java(receiverAddress.getAddressType().getCode())")
    public abstract AddressEntity toEntity(ReceiverAddressDTO receiverAddress);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract ReceiverAddressDTO toDto(AddressEntity address);
}
