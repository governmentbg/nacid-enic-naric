package bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionParticipationDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionParticipationEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.CommissionMemberMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        CommissionMemberMapper.class,
        IntegerToBooleanMapper.class})
public abstract class CommissionParticipationMapper extends BaseObjectMapper<CommissionParticipationEntity, CommissionParticipationDTO> {
}
