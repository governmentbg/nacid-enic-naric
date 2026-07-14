package bg.duosoft.nacidservicesbe.mapper.regprof;

import bg.duosoft.nacidcoredata.mapper.nomenclature.ProfExperienceDocTypeMapper;
import bg.duosoft.nacidfrontofficedto.services.regprof.ExperienceDocumentDTO;
import bg.duosoft.nacidservicesbe.domain.entity.regprof.RegprofExperienceDocumentEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 15.12.2022
 * Time: 14:15
 */
@Mapper(componentModel = "spring", uses = {
        ProfExperienceDocTypeMapper.class,
        WorkPeriodMapper.class
})
public abstract class ExperienceDocumentMapper extends BaseObjectMapper<RegprofExperienceDocumentEntity, ExperienceDocumentDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "documentIssuer", source = "institutionName")
    @Mapping(target = "documentDate", source = "documentDate")
    @Mapping(target = "documentType", source = "type")
    @Mapping(target = "documentDates", source = "workPeriods")
    public abstract RegprofExperienceDocumentEntity toEntity(ExperienceDocumentDTO experienceDocumentDTO);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract ExperienceDocumentDTO toDto(RegprofExperienceDocumentEntity regprofExperienceDocumentEntity);
}
