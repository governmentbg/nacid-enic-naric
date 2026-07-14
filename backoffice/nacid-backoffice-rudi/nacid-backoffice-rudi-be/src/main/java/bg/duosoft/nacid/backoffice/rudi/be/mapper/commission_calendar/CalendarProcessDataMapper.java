package bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CalendarProcessDataDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.query_result.CalendarProcessDataQR;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CalendarProcessDataMapper extends BaseObjectMapper<CalendarProcessDataQR, CalendarProcessDataDTO> {
}
