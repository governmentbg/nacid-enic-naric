package bg.duosoft.nacid.backoffice.rudi.be.mapper.commission_calendar;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.CommissionCalendarDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.commission_calendar.CalendarMainDataSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.mapper.nomenclatures.ReferenceDataMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ReferenceDataMapper.class})
public abstract class CalendarMainDataSectionMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "sessionNum", source = "sessionNum")
    @Mapping(target = "sessionTime", source = "sessionTime")
    @Mapping(target = "notes", source = "notes")
    @Mapping(target = "userCreated", source = "userCreated")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "status", source = "status")
    public abstract CalendarMainDataSectionDTO toMainDataSection(CommissionCalendarDTO mainDataSectionDTO);

    @InheritInverseConfiguration
    public abstract void overrideCalendarMainData(CalendarMainDataSectionDTO source, @MappingTarget CommissionCalendarDTO target);

}
