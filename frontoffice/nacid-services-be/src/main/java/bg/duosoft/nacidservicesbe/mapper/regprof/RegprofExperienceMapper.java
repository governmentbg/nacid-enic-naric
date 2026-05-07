package bg.duosoft.nacidservicesbe.mapper.regprof;

import bg.duosoft.nacidfrontofficedto.services.regprof.RegprofExperienceDTO;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofExperienceDocumentDateEntity;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofExperienceDocumentEntity;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofExperienceEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.*;

import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 12:26
 */
@Mapper(componentModel = "spring", uses = {
        ExperienceDocumentMapper.class
})
public abstract class RegprofExperienceMapper extends BaseObjectMapper<RegprofExperienceEntity, RegprofExperienceDTO> {

    private static final double daysInYear = 365.25;
    private static final double daysInMonth = 30.4375;
    private static final int millisInADay = 1000 * 3600 * 24;

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "professionName", source = "profession")
    @Mapping(target = "documents", source = "experienceDocuments")
    public abstract RegprofExperienceEntity toEntity(RegprofExperienceDTO regprofExperienceDTO);

    @InheritInverseConfiguration
    public abstract RegprofExperienceDTO toDto(RegprofExperienceEntity regprofExperienceEntity);

    @AfterMapping
    public void afterToEntity(@MappingTarget RegprofExperienceEntity target, RegprofExperienceDTO source){
        long daysSum = 0;
        if(target.getDocuments() != null){
            for(RegprofExperienceDocumentEntity doc: target.getDocuments()) {
                if (doc.getDocumentDates() != null) {
                    for(RegprofExperienceDocumentDateEntity dt:doc.getDocumentDates() ){
                        long secondsFrom = dt.getDateFrom().toEpochSecond(LocalTime.MIN,  ZoneOffset.MIN);
                        long secondsTo = dt.getDateTo().toEpochSecond(LocalTime.MIN,  ZoneOffset.MIN);
                        long millis = (secondsTo*1000) - (secondsFrom*1000);
                        double currentDays = ((millis / millisInADay)*Integer.parseInt(dt.getWorkdayDuration().getPk().getId()))/8;

                        daysSum = daysSum + Math.round(currentDays);
                    }
                }
            }
        }

        int totalYears = (int) (daysSum / daysInYear);
        double days = daysSum % daysInYear;
        int totalMonths = (int) (days / daysInMonth);
        int totalDays = (int) Math.round(days % daysInMonth);
        target.setDays(totalDays);
        target.setMonths(totalMonths);
        target.setYears(totalYears);
    }
}
