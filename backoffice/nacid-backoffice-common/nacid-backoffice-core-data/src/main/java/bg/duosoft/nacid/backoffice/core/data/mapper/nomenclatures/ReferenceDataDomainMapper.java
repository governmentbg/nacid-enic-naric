package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.ReferenceDataDomainEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDomainDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * User: ggeorgiev
 * Date: 14.07.2022
 * Time: 16:14
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class ReferenceDataDomainMapper extends BaseObjectMapper<ReferenceDataDomainEntity, ReferenceDataDomainDTO> {
    @Mapping(target = "domain", source = "domain")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "isFoReplication", source = "foReplicationFlag")
    public abstract ReferenceDataDomainDTO toDto(ReferenceDataDomainEntity dto) ;
}
