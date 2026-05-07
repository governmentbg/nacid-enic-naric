package bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.VCommissionCalendarDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VCommissionCalendarEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class VCommissionCalendarMapper extends BaseObjectMapper<VCommissionCalendarEntity, VCommissionCalendarDTO> {
}
