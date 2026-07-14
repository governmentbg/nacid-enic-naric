package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.*;

/**
 * User: ggeorgiev
 * Date: 14.07.2022
 * Time: 16:14
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class ReferenceDataMapper extends BaseObjectMapper<ReferenceDataEntity, ReferenceDataDTO> {
    @Mapping(target = "id", source = "pk.id")
    @Mapping(target = "domain", source = "pk.domain")
    @Mapping(target = "domainName", source = "referenceDataDomain.name")
    @Mapping(target = "isActive", source = "active")
    public abstract ReferenceDataDTO toDto(ReferenceDataEntity entity);

    @InheritInverseConfiguration
    @Mapping(target = "referenceDataDomain.domain", source = "domain")
    public abstract ReferenceDataEntity toEntity(ReferenceDataDTO dto);

}
