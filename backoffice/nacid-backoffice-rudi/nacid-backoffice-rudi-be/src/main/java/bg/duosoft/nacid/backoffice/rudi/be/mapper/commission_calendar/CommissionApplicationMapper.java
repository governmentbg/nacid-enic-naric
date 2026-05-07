package bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.common.ApplicationAttachedDocMapper;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.CommissionApplicationEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.RudiApplicationMapper;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        RudiApplicationMapper.class, IntegerToBooleanMapper.class, ApplicationAttachedDocMapper.class})
public abstract class CommissionApplicationMapper extends BaseObjectMapper<CommissionApplicationEntity, CommissionApplicationDTO> {
}
