package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataIdNameDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ReferenceDataIdNameMapper extends BaseObjectMapper<ReferenceDataEntity, ReferenceDataIdNameDTO> {
    @Mapping(target = "id", source = "pk.id")
    @Mapping(target = "domain", source = "pk.domain")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "domainName", source = "referenceDataDomain.name")
    public abstract ReferenceDataIdNameDTO toDto(ReferenceDataEntity entity);

    @InheritInverseConfiguration
    @Mapping(target = "referenceDataDomain.domain", source = "domain")
    public abstract ReferenceDataEntity toEntity(ReferenceDataIdNameDTO dto);

}
