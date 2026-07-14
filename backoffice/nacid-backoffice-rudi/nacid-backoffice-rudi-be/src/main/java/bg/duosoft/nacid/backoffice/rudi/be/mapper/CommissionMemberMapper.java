package bg.duosoft.nacid.backoffice.rudi.be.mapper;

import bg.duosoft.nacid.backoffice.core.data.mapper.common.AddressMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionMemberEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionMemberDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ProfGroupMapper;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class, AddressMapper.class, ReferenceDataMapper.class, ProfGroupMapper.class})
public abstract class CommissionMemberMapper extends BaseObjectMapper<CommissionMemberEntity, CommissionMemberDTO> {

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "middleName", source = "secondName")
    public abstract CommissionMemberDTO toDto(CommissionMemberEntity e);
}
