package bg.duosoft.nacidservicesbe.mapper.regprof;

import bg.duosoft.nacidcoredata.mapper.nomenclature.ReferenceDataMapper;
import bg.duosoft.nacidfrontofficedto.services.regprof.WorkPeriodDTO;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofExperienceDocumentDateEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 14:20
 */
@Mapper(componentModel = "spring", uses = {
        ReferenceDataMapper.class
})
public abstract class WorkPeriodMapper extends BaseObjectMapper<RegprofExperienceDocumentDateEntity, WorkPeriodDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "dateFrom", source = "fromDate")
    @Mapping(target = "dateTo", source = "toDate")
    @Mapping(target = "workdayDuration", source = "workDayHours")
    public abstract RegprofExperienceDocumentDateEntity toEntity(WorkPeriodDTO workPeriodDTO) ;

    @InheritInverseConfiguration(name = "toEntity")
    public abstract WorkPeriodDTO toDto(RegprofExperienceDocumentDateEntity regprofExperienceDocumentDateEntity);

}
