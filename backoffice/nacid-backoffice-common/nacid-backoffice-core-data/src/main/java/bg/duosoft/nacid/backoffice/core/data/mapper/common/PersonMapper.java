package bg.duosoft.nacid.backoffice.core.data.mapper.common;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.common.PersonEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CivilIdTypeMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.CountryMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.SettlementMapper;
import bg.duosoft.nacid.backoffice.core.data.util.common.PersonDTODataManager;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        CountryMapper.class,
        SettlementMapper.class,
        CivilIdTypeMapper.class,
        ReferenceDataMapper.class,
        IntegerToBooleanMapper.class,
})
public abstract class PersonMapper extends BaseObjectMapper<PersonEntity, PersonDTO> {
    @Mapping(target = "middleName", source = "secondName")
    public abstract PersonDTO toDto(PersonEntity e);

    @InheritInverseConfiguration
    @Mapping(target = "isActive", source = "isActive", defaultValue = "1")
    public abstract PersonEntity toEntity(PersonDTO dto);

    @BeforeMapping
    protected void beforeToEntity(PersonDTO source, @MappingTarget PersonEntity target) {
        this.overrideDtoData(source);
    }

    public void overrideDtoData(PersonDTO target) {
        PersonDTODataManager.setPredefinedData(target);
    }

}
