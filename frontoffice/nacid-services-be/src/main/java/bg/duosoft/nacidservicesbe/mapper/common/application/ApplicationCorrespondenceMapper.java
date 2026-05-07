package bg.duosoft.nacidservicesbe.mapper.common.application;

import bg.duosoft.nacidcoredata.mapper.ApplicationSubtypeMapper;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationCorrespondenceDTO;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationCorrespondenceEntity;
import bg.duosoft.nacidshared.web.mapper.BaseObjectMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 02.10.2023
 * Time: 15:49
 */
@Mapper(componentModel = "spring", uses = {
        ApplicationSubtypeMapper.class
})
public abstract class ApplicationCorrespondenceMapper extends BaseObjectMapper<ApplicationCorrespondenceEntity, ApplicationCorrespondenceDTO> {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "applicationId", source = "applicationId")
    @Mapping(target = "boAttachedDocId", source = "refId")
    @Mapping(target = "about", source = "about")
    @Mapping(target = "registrationNumber", source = "registrationNumber")
    @Mapping(target = "registrationDate", source = "registrationDate")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "dateRead", source = "dateRead")
    public abstract ApplicationCorrespondenceEntity toEntity(ApplicationCorrespondenceDTO correspondenceDTO);

    @InheritInverseConfiguration
    @Mapping(target = "tempNumber", source = "application.tempNumber")
    @Mapping(target = "applicationSubtype", source = "application.applicationSubtype.id")
    @Mapping(target = "applicationSubtypeName", source = "application.applicationSubtype.name")
    public abstract ApplicationCorrespondenceDTO toDto(ApplicationCorrespondenceEntity applicationCorrespondenceEntity);


}
