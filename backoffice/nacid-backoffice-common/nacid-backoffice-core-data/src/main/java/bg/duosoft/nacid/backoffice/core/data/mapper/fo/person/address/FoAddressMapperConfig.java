package bg.duosoft.nacid.backoffice.core.data.mapper.fo.person.address;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacidfrontofficedto.address.BaseAddress;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@MapperConfig(componentModel = "spring")
public interface FoAddressMapperConfig {

    @Mappings({
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "city", source = "city"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "phone", source = "phone"),
            @Mapping(target = "postCode", source = "postCode"),
            @Mapping(target = "settlement", source = "settlement"),
    })
    void toBaseAddress(@MappingTarget AddressDTO target, BaseAddress source);

}
