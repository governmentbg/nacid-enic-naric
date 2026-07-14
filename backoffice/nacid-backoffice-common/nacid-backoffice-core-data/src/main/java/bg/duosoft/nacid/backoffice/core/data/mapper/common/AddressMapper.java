package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.AddressEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.util.common.AddressDTODataManager;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CountryMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.SettlementMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, SettlementMapper.class, ReferenceDataMapper.class})
public abstract class AddressMapper extends BaseObjectMapper<AddressEntity, AddressDTO> {

    public abstract AddressDTO toDto(AddressEntity e);

    @InheritInverseConfiguration
    public abstract AddressEntity toEntity(AddressDTO dto);

    @BeforeMapping
    protected void beforeToEntity(AddressDTO source, @MappingTarget AddressEntity target) {
        this.overrideDtoData(source);
    }

    public void overrideDtoData(AddressDTO target) {
        AddressDTODataManager.setPredefinedData(target);
    }

}
