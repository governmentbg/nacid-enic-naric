package bg.duosoft.nacidservicesbe.mapper.common.application;

import bg.duosoft.nacidcoredata.mapper.FoApplicationStatusMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationListFilterDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationFilter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.12.2022
 * Time: 15:50
 */
@Mapper(componentModel = "spring", uses = {
        FoApplicationStatusMapper.class
})
public abstract class ApplicationListFilterMapper {

    @Autowired
    private FoApplicationStatusMapper foApplicationStatusMapper;

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "foStatusCodes", source = "foStatuses")
    @Mapping(target = "foStatusCodesExclude", source = "foStatusesExclude")
    @Mapping(target = "applicationTypeCode", source = "applicationType.code")
    @Mapping(target = "applicationSubtypeCode", source = "applicationSubtype.code")
    @Mapping(target = "applicationSAR", expression = "java(applicationListFilterDTO.getApplicationSubtype() != null && applicationListFilterDTO.getApplicationSubtype().equals(bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationSubtype.UNI_CHECKS))")
    public abstract ApplicationFilter toEntity(ApplicationListFilterDTO applicationListFilterDTO);

    @AfterMapping
    public void afterToEntity(@MappingTarget ApplicationFilter target, ApplicationListFilterDTO source){
        if(StringUtils.hasText(source.getFoStatusSelectValue()) && (source.getFoStatuses() == null || source.getFoStatuses().size() == 0)) {
            if (source.getFoStatusSelectValue().equals(ApplicationListFilterDTO.SUBMITTED_COMBINED_STATUS_VALUE)) {
                target.setFoStatusCodes(foApplicationStatusMapper.toEntityList(Arrays.asList(FoApplicationStatus.SUBMITTED_WITH_SIGNATURE, FoApplicationStatus.SUBMITTED)));
            } else {
                target.setFoStatusCodes(foApplicationStatusMapper.toEntityList(Arrays.asList(FoApplicationStatus.valueOf(source.getFoStatusSelectValue()))));
            }
        }
    }
}
