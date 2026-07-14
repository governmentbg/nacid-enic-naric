package bg.duosoft.nacidservicesbe.mapper.common.address;

import bg.duosoft.nacidcoredata.mapper.nomenclature.CountryMapper;
import bg.duosoft.nacidcoredata.mapper.nomenclature.SettlementMapper;
import bg.duosoft.nacidfrontofficedto.address.BaseAddress;
import bg.duosoft.nacidservicesbe.domain.entity.common.AddressEntity;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 03.11.2022
 * Time: 15:33
 */
@MapperConfig(componentModel = "spring",
        uses = {
                CountryMapper.class,
                SettlementMapper.class
        }
)
public interface BaseAddressMapper {

    @Mappings({
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "city", source = "city"),
            @Mapping(target = "citySettlement", source = "settlement"),
            @Mapping(target = "postCode", source = "postCode"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "phone", source = "phone"),
    })
    void baseAddressMapping(@MappingTarget AddressEntity addressEntity, BaseAddress baseAddress);
}
