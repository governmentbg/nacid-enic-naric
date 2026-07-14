package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class ReferenceDataMapper extends BaseObjectMapper<ReferenceDataEntity, ReferenceDataDTO> {

    @Mapping(target = "id", source = "pk.id")
    @Mapping(target = "domain", source = "pk.domain")
    @Mapping(target = "domainName", source = "referenceDataDomain.name")
    @Mapping(target = "isActive", source = "active")
    public abstract ReferenceDataDTO toDto(ReferenceDataEntity referenceDataEntity);

    @Mapping(target = "pk.id", source = "id")
    @Mapping(target = "pk.domain", source = "domain")
    @Mapping(target = "active", source = "isActive")
    public abstract ReferenceDataEntity toEntity(ReferenceDataDTO dto);
}
