package bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.WorkCalendarHolidayEntity;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.WorkCalendarHolidayDTO;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import bg.duosoft.nacidshared.web.mapper.IntegerToBooleanMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IntegerToBooleanMapper.class})
public abstract class WorkCalendarHolidayMapper extends BaseObjectMapper<WorkCalendarHolidayEntity, WorkCalendarHolidayDTO> {
}
