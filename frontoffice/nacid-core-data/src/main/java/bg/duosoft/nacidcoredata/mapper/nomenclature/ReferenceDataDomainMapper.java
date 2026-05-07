package bg.duosoft.nacidcoredata.mapper.nomenclature;

import bg.duosoft.nacidcoredata.domain.entity.nomenclature.ReferenceDataDomainEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.ReferenceDataDomainDTO;
import org.mapstruct.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 16.09.2022
 * Time: 13:07
 */
@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class ReferenceDataDomainMapper extends BaseObjectMapper<ReferenceDataDomainEntity, ReferenceDataDomainDTO> {

    public abstract ReferenceDataDomainDTO toDto(ReferenceDataDomainEntity entity);
}
